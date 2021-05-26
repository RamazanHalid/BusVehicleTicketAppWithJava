package com.example.busvehicletickets.dto;

import java.io.Serializable;

public class TicketDto implements Serializable {
    private String travelId;
    private String statusOfTicket;
    private TravelDto travelDto;

    public TicketDto(){

    }

    public TicketDto(String travelId, String statusOfTicket, TravelDto travelDto) {
        this.travelId = travelId;
        this.statusOfTicket = statusOfTicket;
        this.travelDto = travelDto;
    }

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getStatusOfTicket() {
        return statusOfTicket;
    }

    public void setStatusOfTicket(String statusOfTicket) {
        this.statusOfTicket = statusOfTicket;
    }

    public TravelDto getTravelDto() {
        return travelDto;
    }

    public void setTravelDto(TravelDto travelDto) {
        this.travelDto = travelDto;
    }
}
