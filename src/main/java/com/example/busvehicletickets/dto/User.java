package com.example.busvehicletickets.dto;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties


public class User {

    public String nameSurname;
    public String phoneNumber;

    public User() {
    }

    public User(String nameSurname, String phoneNumber) {
        this.nameSurname = nameSurname;
        this.phoneNumber = phoneNumber;
    }
}
