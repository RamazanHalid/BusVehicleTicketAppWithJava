package com.example.busvehicletickets.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.busvehicletickets.Adapters.TravelDtoForFavoriteTicketsAdapter;
import com.example.busvehicletickets.Adapters.TravelDtoForPreviousTicketsAdapter;
import com.example.busvehicletickets.R;
import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.TravelDto;
import com.example.busvehicletickets.dto.UserDto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class PreviousTicketActivity extends AppCompatActivity {
    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Intent toPreviousTicket;
    private ListView listView2;
    UserDto userDto;
    private TravelDtoForPreviousTicketsAdapter mAdapter;
    private ArrayList<TravelDto> travelDtoArrayList;
    ArrayList<TicketDto> ticketDtoArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_ticket2);

        toPreviousTicket = new Intent(PreviousTicketActivity.this, PreviousTicketDetailsActivity.class);
        ticketDtoArrayList = new ArrayList<>();
        travelDtoArrayList = new ArrayList<>();
        listView2 = (ListView) findViewById(R.id.previous_search_resultList);
        myRef.collection("users")

                .document(mAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                         userDto = task.getResult().toObject(UserDto.class);
                        //            ArrayList<TicketDto> ticketDtoArrayList = userDto.getTicketDtoArrayList();
                        //      TravelDto travelDto = userDto.getTicketDtoArrayList().get(0).getTravelDto();

                        for (int i = 0; i < userDto.getTicketDtoArrayList().size(); i++) {
                            if (userDto.getTicketDtoArrayList().get(i).getStatusOfTicket().equals("previous")){
                                travelDtoArrayList.add(userDto.getTicketDtoArrayList().get(i).getTravelDto());
                                ticketDtoArrayList.add(userDto.getTicketDtoArrayList().get(i));

                            }



                        }

                        mAdapter = new TravelDtoForPreviousTicketsAdapter(PreviousTicketActivity.this,travelDtoArrayList);
                        listView2.setAdapter(mAdapter);
                         listView2.setOnItemClickListener(listClick);

                    }
                });
    }
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            toPreviousTicket.putExtra("previousTicket", ticketDtoArrayList.get(position));
            startActivity(toPreviousTicket);
        }
    };
}