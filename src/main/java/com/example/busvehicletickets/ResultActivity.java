package com.example.busvehicletickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.TravelDto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ResultActivity extends AppCompatActivity {
    private TravelDto travelDto;
    private String travelDocumentIdFromResultPage;
    private FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        travelDto = (TravelDto) intent.getSerializableExtra("travelDetails2");
        travelDocumentIdFromResultPage = intent.getStringExtra("travelDocumentId");
                TextView fromAndToCity = (TextView) findViewById(R.id.result_FromAndToCities);
        fromAndToCity.setText(travelDto.getFromCity().toUpperCase() + " ---> " + travelDto.getToCity().toUpperCase());

        TextView dateOfTravel = (TextView) findViewById(R.id.result_dateOfTravel);
        dateOfTravel.setText(travelDto.getDate());

        TextView leavingTime = (TextView) findViewById(R.id.result_leavingTime);
        leavingTime.setText("Leaving Time\n" + travelDto.getLeavingTime());

        TextView travelTime = (TextView) findViewById(R.id.result_travelTime);
        travelTime.setText("Travel Time\n" + travelDto.getTravelTime()+"h");

        TextView travelDistance = (TextView) findViewById(R.id.result_distance);
        travelDistance.setText("Distance\n" + travelDto.getDistance() + "km");



        TextView chairNumber = (TextView) findViewById(R.id.result_chairNumber);

        Intent intent2 = getIntent();
        String seatNumber = intent2.getStringExtra("seatNumber");


        travelDto.setChairNumber(seatNumber);

        chairNumber.setText("Chair Number\n" + travelDto.getChairNumber());

        TextView price = (TextView) findViewById(R.id.result_price);
        price.setText("Price\n" + travelDto.getPrice() + "TL");





    }
    public void buyTheTicket(View view){
        FirebaseUser user = mAuth.getCurrentUser();
        TicketDto ticketDto = new TicketDto(travelDocumentIdFromResultPage,"bought",travelDto);
        ArrayList<TicketDto> ticketDtoArrayList = new ArrayList<>();
        ticketDtoArrayList.add(ticketDto);
        myRef.collection("users")
                .document(user.getUid())
                .update("ticketDtoArrayList", FieldValue.arrayUnion(ticketDto));
    }
}