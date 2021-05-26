package com.example.busvehicletickets.dto;

import java.io.Serializable;

public class TravelDto implements Serializable {

    private String fromCity;
    private String toCity;
    private String distance;
    private String price;
    private String date;
    private String travelTime;
    private String leavingTime;
    private String chairNumber;

    public TravelDto(){

    }

    public TravelDto(String fromCity, String toCity, String distance, String price, String date, String travelTime, String leavingTime) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
        this.price = price;
        this.date = date;
        this.travelTime = travelTime;
        this.leavingTime = leavingTime;
    }

    public String getChairNumber() {
        return chairNumber;
    }

    public void setChairNumber(String chairNumber) {
        this.chairNumber = chairNumber;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(String leavingTime) {
        this.leavingTime = leavingTime;
    }

}
