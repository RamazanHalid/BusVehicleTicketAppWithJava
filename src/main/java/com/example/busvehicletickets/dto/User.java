package com.example.busvehicletickets.dto;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties


public class User {

    public String nameSurname;
    public String phoneNumber;
    public String gender;


    public User() {
    }
    public User(String nameSurname, String phoneNumber, String gender) {
        this.nameSurname = nameSurname;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getGender() {
        return gender;
    }
}
