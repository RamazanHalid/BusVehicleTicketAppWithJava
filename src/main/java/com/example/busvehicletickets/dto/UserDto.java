package com.example.busvehicletickets.dto;

import java.util.ArrayList;

public class UserDto {
   private String nameSurname;
   private String phoneNumber;
   private String gender;
   private ArrayList<TicketDto> ticketDtoArrayList;

    public UserDto(String nameSurname, String phoneNumber, String gender, ArrayList<TicketDto> ticketDtoArrayList) {
        this.nameSurname = nameSurname;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.ticketDtoArrayList = ticketDtoArrayList;
    }
    public UserDto(){

    }
    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<TicketDto> getTicketDtoArrayList() {
        return ticketDtoArrayList;
    }

    public void setTicketDtoArrayList(ArrayList<TicketDto> ticketDtoArrayList) {
        this.ticketDtoArrayList = ticketDtoArrayList;
    }

}
