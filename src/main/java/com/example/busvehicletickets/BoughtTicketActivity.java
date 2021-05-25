package com.example.busvehicletickets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.TravelDto;
import com.example.busvehicletickets.dto.UserDto;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BoughtTicketActivity extends AppCompatActivity {

    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ListView listView;
    private TravelDtoAdapter mAdapter;
    private ArrayList<TravelDto> travelDtoArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bought_ticket);

        listView = (ListView) findViewById(R.id.search_resultList);
        travelDtoArrayList = new ArrayList<>();
       // travelDtoArrayList.add(new TravelDto("asdfaf","asdfaf","asdfaf","asdfaf","asdfaf","asdfaf","asdfaf"));

        myRef.collection("users")

                .document("2uXLbB9kMYdr50RSddgIOKnx3uu2")
                //.document(mAuth.getCurrentUser().getUid())

                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        UserDto userDto = documentSnapshot.toObject(UserDto.class);
                        ArrayList<TicketDto> ticketDtoArrayList = userDto.getTicketDtoArrayList();

                        for (int i = 0; i < ticketDtoArrayList.size(); i++) {
                            if (ticketDtoArrayList.get(i).getStatusOfTicket().equals("bought")){
                                travelDtoArrayList.add(ticketDtoArrayList.get(i).getTravelDto());

                            }

                           // travelDtoArrayList.add(new TravelDto("asdfaf","asdfaf","asdfaf","asdfaf","asdfaf","asdfaf","asdfaf"));
                            mAdapter = new TravelDtoAdapter(BoughtTicketActivity.this,travelDtoArrayList);
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