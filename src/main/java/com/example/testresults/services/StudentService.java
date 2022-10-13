package com.example.testresults.services;

import com.example.testresults.objects.Student;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StudentService {
  private ArrayList<Student> studentList = new ArrayList<>();
  private Integer id = 0;


  public List<Student> getStudents() {
    return studentList;
  }

  public Student addStudent(Student student) {
    student.setId(id);
    studentList.add(student);
    id++;
    return student;
  }

  public void deleteStudent(int id) {
    Mono.just(id).map(this::findStudent).map(student -> studentList.remove(student));
  }

  public Student findStudent(int id) {
    return studentList.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
  }

  public Mono<Void> patchStudent(int id, Student studentData) {
    return Mono.just(id).map(this::findStudent).map(student -> {
      student.setFirstName(studentData.getFirstName());
      student.setLastName(studentData.getLastName());
      student.setTestScore1(studentData.getTestScore1());
      student.setTestScore2(studentData.getTestScore2());
      student.setTestScore3(studentData.getTestScore3());
      return student;
    }).then();
  }

  public List<Student> getStudentsby(String scoreType) {
    if(scoreType.equals("studentId")) {
      return studentList.stream().sorted(Comparator.comparingInt(Student::getId).reversed()).collect(
          Collectors.toList());
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
