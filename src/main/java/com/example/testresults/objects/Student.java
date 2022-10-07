package com.example.testresults.objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Student {
    int id;
    String firstName;
    String lastName;
    int testScore1;
    int testScore2;
    int testScore3;
    int overallRanking;


    public int getOverallRanking() {
        return(testScore1+testScore2+testScore3);
    }
}