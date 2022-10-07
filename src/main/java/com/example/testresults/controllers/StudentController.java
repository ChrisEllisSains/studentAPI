package com.example.testresults.controllers;

import com.example.testresults.objects.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class StudentController {


    private ArrayList<Student> studentList = new ArrayList<>();
    private Integer id = 0;

    //POST that creates a student object
    @PostMapping("students")
    public Mono<Student> addStudent(@RequestBody Student student) {
        return Mono.just(student).map(this::createStudent);
    }

    private Student createStudent(Student student) {
        student.setId(id);
        studentList.add(student);
        id++;
        return student;
    }

    //PATCH that takes a studentId and a request body and updates the person with that corresponding id to all the new values provided in the body
    @PatchMapping("students/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> updateStudent(@PathVariable int id, @RequestBody Student studentData) {
        return Mono.just(id).map(this::findStudent).map(student -> {
                student.setFirstName(studentData.getFirstName());
                student.setLastName(studentData.getLastName());
                student.setTestScore1(studentData.getTestScore1());
                student.setTestScore2(studentData.getTestScore2());
                student.setTestScore3(studentData.getTestScore3());
                return student;
        }).then();
    }

    private Student findStudent(int id) {
        return studentList.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
    }

    //GET that returns all existing students
    @GetMapping("students")
    public List<Student> getStudents() {
        return studentList;
    }
    //GET that returns a specific student given an Id
    @GetMapping("students/{id}")
    public Mono<Student> getStudent(@PathVariable int id) {
        return Mono.just(findStudent(id));
    }

    //DELETE that removes a student from the service
    @DeleteMapping("students/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteStudent(@PathVariable int id) {
        Mono.just(id).map(this::findStudent).map(student -> studentList.remove(student));
    }

    //GET that returns a list of all students in ascending order based on the operation provided /students/(studentId OR testScore1 OR testScore2 OR testScore3 OR overRanking)
    @GetMapping("students/orderedBy/{scoreType:studentId|testScore1|testScore2|testScore3|overallRanking}")
    public List<Student> getStudentsby(@PathVariable String scoreType) {
        if(scoreType.equals("studentId")) {
            return studentList.stream().sorted(Comparator.comparingInt(Student::getId).reversed()).collect(Collectors.toList());
        }
        else if(scoreType.equals("testScore1")) {
            return studentList.stream().sorted(Comparator.comparingInt(Student::getTestScore1).reversed()).collect(Collectors.toList());
        }
        else if(scoreType.equals("testScore2")) {
            return studentList.stream().sorted(Comparator.comparingInt(Student::getTestScore2).reversed()).collect(Collectors.toList());
        }
        else if(scoreType.equals("testScore3")) {
            return studentList.stream().sorted(Comparator.comparingInt(Student::getTestScore3).reversed()).collect(Collectors.toList());
        }
        else if(scoreType.equals("overallRanking")) {
            return studentList.stream().sorted(Comparator.comparingInt(Student::getOverallRanking).reversed()).collect(Collectors.toList());
        }
        else {
            return null;
        }
    }
}
