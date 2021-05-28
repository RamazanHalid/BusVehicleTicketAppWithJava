package com.example.busvehicletickets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.busvehicletickets.Adapters.TravelDtoForCanceledTicketsAdapter;
import com.example.busvehicletickets.Adapters.TravelDtoForFavoriteTicketsAdapter;
import com.example.busvehicletickets.R;
import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.TravelDto;
import com.example.busvehicletickets.dto.UserDto;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CanceledTicketActivity extends AppCompatActivity {
    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ListView listView2;
    Intent toCanceledTicket;
    ArrayList<TicketDto> ticketDtoArrayList;
    private TravelDtoForCanceledTicketsAdapter mAdapter;
    private ArrayList<TravelDto> travelDtoArrayList;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canceled_ticket);
        toCanceledTicket = new Intent(CanceledTicketActivity.this, CanceledTicketDetailsActivity.class);
        listView2 = (ListView) findViewById(R.id.search_resultList);
        ticketDtoArrayList = new ArrayList<>();
        travelDtoArrayList = new ArrayList<>();

        myRef.collection("users")

                .document(mAuth.getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                            UserDto userDto = documentSnapshot.toObject(UserDto.class);



                            for (int i = 0; i < userDto.getTicketDtoArrayList().size(); i++) {
                                if (userDto.getTicketDtoArrayList().get(i).getStatusOfTicket().equals("canceled")){
                                    travelDtoArrayList.add(userDto.getTicketDtoArrayList().get(i).getTravelDto());
                                    ticketDtoArrayList.add(userDto.getTicketDtoArrayList().get(i));
                                    }

                            mAdapter = new TravelDtoForCanceledTicketsAdapter(CanceledTicketActivity.this,travelDtoArrayList);
                            listView2.setAdapter(mAdapter);
                            listView2.setOnItemClickListener(listClick);

                        }




                    }



                });
    }
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            toCanceledTicket.putExtra("canceledTicket", ticketDtoArrayList.get(position));
            startActivity(toCanceledTicket);

        }
    };
}