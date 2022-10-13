package com.example.testresults.controllers;

import com.example.testresults.objects.Student;
import com.example.testresults.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class StudentController {


    private StudentService studentService = new StudentService();
//    private ArrayList<Student> studentList = new ArrayList<>();
//    private Integer id = 0;

    //POST that creates a student object
    @PostMapping("students")
    public Mono<Student> addStudent(@RequestBody Student student) {
        return Mono.just(studentService.addStudent(student));
    }

//    private Student createStudent(Student student) {
//        student.setId(id);
//        studentList.add(student);
//        id++;
//        return student;
//    }

    //PATCH that takes a studentId and a request body and updates the person with that corresponding id to all the new values provided in the body
    @PatchMapping("students/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> updateStudent(@PathVariable int id, @RequestBody Student studentData) {
        return studentService.patchStudent(id, studentData);
    }

    //GET that returns all existing students
    @GetMapping("students")
    public Flux<Student> getStudents() {
        return Flux.fromIterable(studentService.getStudents());
    }
    //GET that returns a specific student given an Id
    @GetMapping("students/{id}")
    public Mono<Student> getStudent(@PathVariable int id) {
        return Mono.just(studentService.findStudent(id));
    }

    //DELETE that removes a student from the service
    @DeleteMapping("students/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

    //GET that returns a list of all students in ascending order based on the operation provided /students/(studentId OR testScore1 OR testScore2 OR testScore3 OR overRanking)
    @GetMapping("students/orderedBy/{scoreType:studentId|testScore1|testScore2|testScore3|overallRanking}")
    public List<Student> getStudentsby(@PathVariable String scoreType) {
        return studentService.getStudentsby(scoreType);
    }
}
