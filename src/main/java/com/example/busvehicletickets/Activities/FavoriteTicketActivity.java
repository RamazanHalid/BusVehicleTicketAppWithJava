package com.example.busvehicletickets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.busvehicletickets.R;
import com.example.busvehicletickets.Adapters.TravelDtoForFavoriteTicketsAdapter;
import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.TravelDto;
import com.example.busvehicletickets.dto.UserDto;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FavoriteTicketActivity extends AppCompatActivity {
    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ListView listView2;
    private TravelDtoForFavoriteTicketsAdapter mAdapter;
    private ArrayList<TravelDto> travelDtoArrayList;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_ticket);
        listView2 = (ListView) findViewById(R.id.search_resultList);

        travelDtoArrayList = new ArrayList<>();

        myRef.collection("users")

                .document(mAuth.getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        UserDto userDto = documentSnapshot.toObject(UserDto.class);
                        ArrayList<TicketDto> ticketDtoArrayList = userDto.getTicketDtoArrayList();
                        TravelDto travelDto = userDto.getTicketDtoArrayList().get(0).getTravelDto();
                        System.out.println(travelDto);
                        System.out.println(userDto.getTicketDtoArrayList().size());
                        for (int i = 0; i < ticketDtoArrayList.size(); i++) {
                            if (ticketDtoArrayList.get(i).getStatusOfTicket().equals("favorite")){
                                travelDtoArrayList.add(ticketDtoArrayList.get(i).getTravelDto());

                            }





                        }  mAdapter = new TravelDtoForFavoriteTicketsAdapter(FavoriteTicketActivity.this,travelDtoArrayList);
                        listView2.setAdapter(mAdapter);
                        listView2.setOnItemClickListener(listClick);




                    }



                });

    }
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            System.out.println(position);

        }
    };
    }
