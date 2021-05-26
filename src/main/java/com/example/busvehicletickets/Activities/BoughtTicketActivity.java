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
    UserDto userDto;
    private TravelDtoForBoughtTicketsAdapter mAdapter;
    private ArrayList<TravelDto> travelDtoArrayList;
    ArrayList<TicketDto> ticketDtoArrayList;
    Intent intentForCancelingTheTicket;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bought_ticket);


        intentForCancelingTheTicket = new Intent(BoughtTicketActivity.this, CancelTheTicketActivity.class);
        ticketDtoArrayList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.search_resultList);

        travelDtoArrayList = new ArrayList<>();

        myRef.collection("users")

                .document(mAuth.getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        userDto = documentSnapshot.toObject(UserDto.class);
                       // ticketDtoArrayList = userDto.getTicketDtoArrayList();

                        for (int i = 0; i < userDto.getTicketDtoArrayList().size(); i++) {
                            if (userDto.getTicketDtoArrayList().get(i).getStatusOfTicket().equals("bought")){
                               // System.out.println(userDto.getTicketDtoArrayList().get(i).getStatusOfTicket());
                               // System.out.println(userDto.getTicketDtoArrayList().get(i).getTravelDto().getChairNumber());
                                //System.out.println(ticketDtoArrayList.get(i).getTravelDto().getChairNumber());
                                travelDtoArrayList.add(userDto.getTicketDtoArrayList().get(i).getTravelDto());
                                ticketDtoArrayList.add(userDto.getTicketDtoArrayList().get(i));
                                System.out.println(travelDtoArrayList.toString());
                            }

                            System.out.println("working 2");
                            mAdapter = new TravelDtoForBoughtTicketsAdapter(BoughtTicketActivity.this,travelDtoArrayList);
                            //   intentForCancelingTheTicket.putExtra("canceledTicket", )

                            listView.setAdapter(mAdapter);
                            listView.setOnItemClickListener(listClick);


                        }



                    }
                    AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           intentForCancelingTheTicket.putExtra("canceledTicket", ticketDtoArrayList.get(position));
                           startActivity(intentForCancelingTheTicket);

                        }
                    };

                });

    }

}