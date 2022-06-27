package com.pratiksha.connectmongodb.models;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// import lombok.Data;

// @Data
@Document("Student")  
public class Student 
{
    @Id
    
    private String _id;

    @NotNull
    @Size(min = 2,message = "name should have atleast 2 characters")
    String name;

    @NotNull
    Address address;

    @NotNull
    String college;

    List<Subject> favouriteSub;

    public Student()
    {
    }

    public Student(String _id, String name,Address address,String college, List<Subject> favouriteSub) {
        this._id = _id;
        this.name = name;
        this.address = address;
        this.college = college;
        this.favouriteSub = favouriteSub;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public List<Subject> getFavouriteSub() {
        return favouriteSub;
    }

    public void setFavouriteSub(List<Subject> favouriteSub) {
        this.favouriteSub = favouriteSub;
    }
}
