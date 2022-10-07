package com.example.testresults.controllers;

import com.example.testresults.objects.Student;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class StudentController {

    private Map<Integer, Student> studentList = new HashMap<Integer, Student>();
    private Integer id = 0;

    //POST that creates a student object
    @PostMapping("students")
    public void addStudent(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int testScore1, @RequestParam int testScore2, @RequestParam int testScore3) {
        studentList.put(id, new Student(id, firstName, lastName, testScore1, testScore2, testScore3));
        id++;
    }

    //PATCH that takes a studentId and a request body and updates the person with that corresponding id to all the new values provided in the body
    @PatchMapping("students/{id}")
    public void updateStudent(@PathVariable int id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam int testScore1, @RequestParam int testScore2, @RequestParam int testScore3) {
        studentList.put(id, new Student(id, firstName, lastName, testScore1, testScore2, testScore3));
    }

    //GET that returns all existing students
    @GetMapping("students")
    public List<Student> getStudents() {
        return new ArrayList<Student>(studentList.values());
    }
    //GET that returns a specific student given an Id
    @GetMapping("students/{id}")
    public Student getStudent(@PathVariable int id) {
        return studentList.get(id);
    }

    //DELETE that removes a student from the service
    @DeleteMapping("students/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentList.remove(id);
    }

    //GET that returns a list of all students in ascending order based on the operation provided /students/(studentId OR testScore1 OR testScore2 OR testScore3 OR overRanking)
    @GetMapping("students/orderedBy/{scoreType}")
    public List<Student> getStudentsby(@PathVariable String scoreType) {
        if(scoreType.equals("studentId")) {
            return new ArrayList<Student>(studentList.values().stream().sorted(Comparator.comparingInt(Student::getId)).collect(Collectors.toList()));
        }
        else if(scoreType.equals("testScore1")) {
            return new ArrayList<Student>(studentList.values().stream().sorted(Comparator.comparingInt(Student::getTestScore1)).collect(Collectors.toList()));
        }
        else if(scoreType.equals("testScore2")) {
            return new ArrayList<Student>(studentList.values().stream().sorted(Comparator.comparingInt(Student::getTestScore2)).collect(Collectors.toList()));
        }
        else if(scoreType.equals("testScore3")) {
            return new ArrayList<Student>(studentList.values().stream().sorted(Comparator.comparingInt(Student::getTestScore3)).collect(Collectors.toList()));
        }
        else if(scoreType.equals("overallRanking")) {
            return new ArrayList<Student>(studentList.values().stream().sorted(Comparator.comparingInt(Student::getOverallRanking)).collect(Collectors.toList()));
        }
        else {
            return null;
        }
    }
}
