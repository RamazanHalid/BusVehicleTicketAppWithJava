package com.example.busvehicletickets.dto;

public class TicketDto {
    private String travelId;
    private String statusOfTicket;
    private TravelDto travelDto;

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
