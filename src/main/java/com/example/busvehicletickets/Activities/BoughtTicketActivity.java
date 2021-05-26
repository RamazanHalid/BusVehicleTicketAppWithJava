package com.example.busvehicletickets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;

import com.example.busvehicletickets.R;
import com.example.busvehicletickets.Adapters.TravelDtoForBoughtTicketsAdapter;
import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.TravelDto;
import com.example.busvehicletickets.dto.UserDto;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;

public class BoughtTicketActivity extends AppCompatActivity {

    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ListView listView;
    private TravelDtoForBoughtTicketsAdapter mAdapter;
    private ArrayList<TravelDto> travelDtoArrayList;
    ArrayList<TicketDto> ticketDtoArrayList;
    Intent intentForCancelingTheTicket;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bought_ticket);
        TableLayout tableLayout = (TableLayout) findViewById(R.id.www);


        intentForCancelingTheTicket = new Intent(BoughtTicketActivity.this, CancelTheTicketActivity.class);


        listView = (ListView) findViewById(R.id.search_resultList);

        travelDtoArrayList = new ArrayList<>();

        myRef.collection("users")

                .document(mAuth.getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        UserDto userDto = documentSnapshot.toObject(UserDto.class);
                        ticketDtoArrayList = userDto.getTicketDtoArrayList();

                        for (int i = 0; i < ticketDtoArrayList.size(); i++) {
                            if (ticketDtoArrayList.get(i).getStatusOfTicket().equals("bought")){
                                travelDtoArrayList.add(ticketDtoArrayList.get(i).getTravelDto());

                            }


                            mAdapter = new TravelDtoForBoughtTicketsAdapter(BoughtTicketActivity.this,travelDtoArrayList);
                         //   intentForCancelingTheTicket.putExtra("canceledTicket", )

                            listView.setAdapter(mAdapter);
                            listView.setOnItemClickListener(listClick);
                        }





                    }


                });

    }
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            intentForCancelingTheTicket.putExtra("canceledTicket", ticketDtoArrayList.get(position));
            startActivity(intentForCancelingTheTicket);

        }
    };
}