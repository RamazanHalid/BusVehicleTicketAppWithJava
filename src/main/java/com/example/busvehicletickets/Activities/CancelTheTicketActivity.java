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

import com.example.busvehicletickets.Fragments.MenuFragment;
import com.example.busvehicletickets.R;
import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.TravelDto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class CancelTheTicketActivity extends AppCompatActivity {
    TicketDto ticketDto;
    TravelDto travelDto;
    private Intent toSearchPageIntent2;
    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String travelId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_the_ticket);

        Intent intent = getIntent();
        ticketDto = (TicketDto) intent.getSerializableExtra("canceledTicket");
        travelDto = ticketDto.getTravelDto();
        travelId = ticketDto.getTravelId();

        TextView fromAndToCity = (TextView) findViewById(R.id. cancelATicket_FromAndToCities);
        fromAndToCity.setText( travelDto.getFromCity().toUpperCase()+ " ---> " + travelDto.getToCity().toUpperCase());



        TextView dateOfTravel = (TextView) findViewById(R.id.cancelATicket_dateOfTravel);
        dateOfTravel.setText(travelDto.getDate());

        TextView leavingTime = (TextView) findViewById(R.id.cancelATicket_leavingTime);
        leavingTime.setText("Leaving Time\n" + travelDto.getLeavingTime());

        TextView ticketPrice = (TextView) findViewById(R.id.cancelATicket_price);
        ticketPrice.setText("Price\n" + travelDto.getPrice() + "TL");

        TextView distanceOfTicket = (TextView) findViewById(R.id.cancelATicket_distance);
        distanceOfTicket.setText("Distance\n" + travelDto.getDistance() + "km");

        TextView chairNumber = (TextView) findViewById(R.id.cancelATicket_chairNumber);
        chairNumber.setText("Chair Number\n" + travelDto.getChairNumber());

        TextView travelTime = (TextView) findViewById(R.id.cancelATicket_travelTime);
        travelTime.setText("Travel Time\n" + travelDto.getTravelTime()+"h");



    }
    public void cancelTheTicket(View view){


        TicketDto ticketDto2 = new TicketDto(travelId,"canceled",travelDto);
        myRef.collection("users")
                .document(mAuth.getCurrentUser().getUid())
                .update("ticketDtoArrayList", FieldValue.arrayRemove(ticketDto)
                ,"ticketDtoArrayList" , FieldValue.arrayUnion(ticketDto2));



        myRef.collection("users")
                .document(mAuth.getCurrentUser().getUid())
                .update("ticketDtoArrayList", FieldValue.arrayUnion(ticketDto2));

        doSeatUnavailable(travelDto.getChairNumber(),"available");
        toSearchPageIntent2 = new Intent(CancelTheTicketActivity.this, MainActivity2.class);

        Toast.makeText(this, "Ticket CANCELED!", Toast.LENGTH_LONG).show();
        startActivity(toSearchPageIntent2);
        finish();
    }
    public void doSeatUnavailable(String seatNumberM, String statusOfTicketM){
        myRef.collection("travels")
                .document(travelId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        ArrayList<HashMap<String,Object>> resultList = (ArrayList<HashMap<String, Object>>) task.getResult().get("seats");
                        resultList.get(0).replace(seatNumberM ,statusOfTicketM);
                        myRef.collection("travels")
                                .document(travelId)
                                .update("seats", resultList);

                    }
                });
    }

}