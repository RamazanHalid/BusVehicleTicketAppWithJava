package com.example.busvehicletickets.dto;

public class TravelDetails {
    private String distance;
    private String price;
    private String travelTime;
    private String leavingTime;

    public TravelDetails(String distance, String price, String travelTime, String leavingTime) {
        this.distance = distance;
        this.price = price;
        this.travelTime = travelTime;
        this.leavingTime = leavingTime;
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
