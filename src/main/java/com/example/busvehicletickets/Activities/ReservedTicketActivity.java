package com.example.busvehicletickets.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.busvehicletickets.R;
import com.example.busvehicletickets.Adapters.TravelDtoForReservedTicketsAdapter;
import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.TravelDto;
import com.example.busvehicletickets.dto.UserDto;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ReservedTicketActivity extends AppCompatActivity {

    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ListView listView;
    Intent toReservedTicket;
    ArrayList<TicketDto> ticketDtoArrayList;
    private TravelDtoForReservedTicketsAdapter mAdapter;
    private ArrayList<TravelDto> travelDtoArrayList;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved_ticket);



        toReservedTicket = new Intent(ReservedTicketActivity.this, ReservationToBuyActivity.class);


        listView = (ListView) findViewById(R.id.reserved_search_resultList);

        travelDtoArrayList = new ArrayList<>();
        ticketDtoArrayList = new ArrayList<>();
        myRef.collection("users")

                .document(mAuth.getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        UserDto userDto = documentSnapshot.toObject(UserDto.class);
                       // ArrayList<TicketDto> ticketDtoArrayList = userDto.getTicketDtoArrayList();

                        for (int i = 0; i < userDto.getTicketDtoArrayList().size(); i++) {
                            if (userDto.getTicketDtoArrayList().get(i).getStatusOfTicket().equals("reserved")){
                                travelDtoArrayList.add(userDto.getTicketDtoArrayList().get(i).getTravelDto());
                                ticketDtoArrayList.add(userDto.getTicketDtoArrayList().get(i));
                                System.out.println(travelDtoArrayList.toString());
                                System.out.println("working1");
                            }
                            System.out.println("working2");

                            mAdapter = new TravelDtoForReservedTicketsAdapter(ReservedTicketActivity.this,travelDtoArrayList);


                            listView.setAdapter(mAdapter);
                            listView.setOnItemClickListener(listClick);
                        }
                        System.out.println("working3");




                    }


                });

    }
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            toReservedTicket.putExtra("reservedTicket", ticketDtoArrayList.get(position));
            startActivity(toReservedTicket);

        }
    };
}