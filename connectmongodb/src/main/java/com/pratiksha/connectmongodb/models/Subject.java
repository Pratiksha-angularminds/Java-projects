package com.pratiksha.connectmongodb.models;

import org.springframework.data.mongodb.core.mapping.Document;

// import lombok.Data;

// @Data
@Document
public class Subject 
{
    String subjectName;
    int marks;


    public Subject() 
    {
    }

    public Subject(String subjectName, int marks) 
    {
        this.subjectName = subjectName;
        this.marks = marks;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

}
