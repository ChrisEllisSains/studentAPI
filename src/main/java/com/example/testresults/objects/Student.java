package com.example.testresults.objects;

public class Student {
    int id;
    String firstName;
    String lastName;
    int testScore1;
    int testScore2;
    int testScore3;
    int overallRanking;

    public Student(int id, String firstName, String lastName, int testScore1, int testScore2, int testScore3) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.testScore1 = testScore1;
        this.testScore2 = testScore2;
        this.testScore3 = testScore3;
        this.overallRanking = testScore1 + testScore2 + testScore3;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getTestScore1() {
        return testScore1;
    }

    public void setTestScore1(Integer testScore1) {
        this.testScore1 = testScore1;
    }

    public Integer getTestScore2() {
        return testScore2;
    }

    public void setTestScore2(Integer testScore2) {
        this.testScore2 = testScore2;
    }

    public Integer getTestScore3() {
        return testScore3;
    }

    public void setTestScore3(Integer testScore3) {
        this.testScore3 = testScore3;
    }

    public Integer getOverallRanking() {
        return overallRanking;
    }

    public void setOverallRanking(Integer overallRanking) {
        this.overallRanking = overallRanking;
    }
}
