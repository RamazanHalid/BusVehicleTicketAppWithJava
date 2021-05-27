package com.example.busvehicletickets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.busvehicletickets.Adapters.TravelDtoForCanceledTicketsAdapter;
import com.example.busvehicletickets.R;
import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.TravelDto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ReservationToBuyActivity extends AppCompatActivity {
    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    TicketDto ticketDto;
    TravelDto travelDto;
    private FirebaseUser user;
    private ListView listView2;
    private TravelDtoForCanceledTicketsAdapter mAdapter;
    private ArrayList<TravelDto> travelDtoArrayList;
    private Intent intent;
    private String travelId;
    private Intent toSearchPageIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_to_buy);

        user   = mAuth.getCurrentUser();
        intent = getIntent();
        ticketDto = (TicketDto) intent.getSerializableExtra("reservedTicket");
        travelDto = ticketDto.getTravelDto();
        travelId = ticketDto.getTravelId();
        TextView fromAndToCity = (TextView) findViewById(R.id.reserved_result_FromAndToCities);
        fromAndToCity.setText(travelDto.getFromCity().toUpperCase() + " ---> " + travelDto.getToCity().toUpperCase());

        TextView dateOfTravel = (TextView) findViewById(R.id.reserved_result_dateOfTravel);
        dateOfTravel.setText(travelDto.getDate());

        TextView leavingTime = (TextView) findViewById(R.id.reserved_result_leavingTime);
        leavingTime.setText("Leaving Time\n" + travelDto.getLeavingTime());

        TextView travelTime = (TextView) findViewById(R.id.reserved_result_travelTime);
        travelTime.setText("Travel Time\n" + travelDto.getTravelTime()+"h");

        TextView travelDistance = (TextView) findViewById(R.id.reserved_result_distance);
        travelDistance.setText("Distance\n" + travelDto.getDistance() + "km");


        TextView chairNumber = (TextView) findViewById(R.id.reserved_result_chairNumber);
        chairNumber.setText("Chair Number\n" + travelDto.getChairNumber());

        TextView price = (TextView) findViewById(R.id.reserved_result_price);
        price.setText("Price\n" + travelDto.getPrice() + "TL");

        TextView reserveTheTicket = (TextView) findViewById(R.id.reserved_result_getReserve);
    }
}