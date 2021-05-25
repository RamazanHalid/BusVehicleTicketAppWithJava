package com.example.busvehicletickets;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

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
    private TravelDtoForReservedTicketsAdapter mAdapter;
    private ArrayList<TravelDto> travelDtoArrayList;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bought_ticket);





        listView = (ListView) findViewById(R.id.search_resultList);

        travelDtoArrayList = new ArrayList<>();

        myRef.collection("users")

                .document("2uXLbB9kMYdr50RSddgIOKnx3uu2")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        UserDto userDto = documentSnapshot.toObject(UserDto.class);
                        ArrayList<TicketDto> ticketDtoArrayList = userDto.getTicketDtoArrayList();

                        for (int i = 0; i < ticketDtoArrayList.size(); i++) {
                            if (ticketDtoArrayList.get(i).getStatusOfTicket().equals("reserved")){
                                travelDtoArrayList.add(ticketDtoArrayList.get(i).getTravelDto());

                            }


                            mAdapter = new TravelDtoForReservedTicketsAdapter(ReservedTicketActivity.this,travelDtoArrayList);


                            listView.setAdapter(mAdapter);
                            listView.setOnItemClickListener(listClick);
                        }





                    }


                });

    }
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        }
    };
}