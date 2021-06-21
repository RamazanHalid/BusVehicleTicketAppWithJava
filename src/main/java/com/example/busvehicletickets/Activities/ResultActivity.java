package com.example.busvehicletickets.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.busvehicletickets.R;
import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.TravelDto;
import com.example.busvehicletickets.dto.UserDto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;


public class ResultActivity extends AppCompatActivity {
    private TravelDto travelDto;
    private String travelDocumentIdFromResultPage;
    private FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String statusOfTicket;
    private Intent toSearchPageIntent;
    private UserDto userDto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        mAuth = FirebaseAuth.getInstance();
        user   = mAuth.getCurrentUser();
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

        TextView addTicketToFavorite = (TextView) findViewById(R.id.result_addFavorite);
        addTicketToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicketDto ticketDto = new TicketDto(travelDocumentIdFromResultPage,"favorite",travelDto);

                myRef.collection("users")
                        .document(user.getUid())
                        .update("ticketDtoArrayList", FieldValue.arrayUnion(ticketDto));
                toSearchPageIntent = new Intent(ResultActivity.this,MainActivity2.class);
                startActivity(toSearchPageIntent);
                Toast.makeText(ResultActivity.this, "Ticket added to favorite!", Toast.LENGTH_LONG).show();
                finish();
            }
        });


        TextView reserveTheTicket = (TextView) findViewById(R.id.result_getReserve);
        reserveTheTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicketDto ticketDto = new TicketDto(travelDocumentIdFromResultPage,"reserved",travelDto);
                myRef.collection("users")
                        .document(user.getUid())
                        .update("ticketDtoArrayList", FieldValue.arrayUnion(ticketDto));


                doSeatUnavailable(travelDto.getChairNumber(), "reserved");
                toSearchPageIntent = new Intent(ResultActivity.this,MainActivity2.class);
                startActivity(toSearchPageIntent);
                Toast.makeText(ResultActivity.this, "Ticket has RESERVED!", Toast.LENGTH_LONG).show();
                finish();
            }
        });




    }
    public void buyTheTicket(View view){

        TicketDto ticketDto = new TicketDto(travelDocumentIdFromResultPage,"booked",travelDto);

        myRef.collection("users")
                .document(user.getUid())
                .update("ticketDtoArrayList", FieldValue.arrayUnion(ticketDto));

       doSeatUnavailable(travelDto.getChairNumber(),"booked");
       toSearchPageIntent = new Intent(ResultActivity.this,MainActivity2.class);
       startActivity(toSearchPageIntent);
        Toast.makeText(this, "Ticket BOUGHT!", Toast.LENGTH_LONG).show();
        finish();
    }


    public void doSeatUnavailable(String seatNumberM, String statusOfTicketM){
        myRef.collection("travels")
                .document(travelDocumentIdFromResultPage)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        ArrayList<HashMap<String,Object>> resultList = (ArrayList<HashMap<String, Object>>) task.getResult().get("seats");
                        resultList.get(0).replace(seatNumberM ,statusOfTicketM);
                        myRef.collection("travels")
                                .document(travelDocumentIdFromResultPage)
                                .update("seats", resultList);



                    }
                });
    }

}