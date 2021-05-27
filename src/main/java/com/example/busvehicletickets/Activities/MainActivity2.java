package com.example.busvehicletickets.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ListView;

import com.example.busvehicletickets.Adapters.TravelDtoForBoughtTicketsAdapter;
import com.example.busvehicletickets.R;
import com.example.busvehicletickets.Adapters.ViewPagerAdapter;
import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.TravelDto;
import com.example.busvehicletickets.dto.User;
import com.example.busvehicletickets.dto.UserDto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class MainActivity2
        extends AppCompatActivity {

    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ListView listView;
    UserDto userDto;
    private TravelDtoForBoughtTicketsAdapter mAdapter;
    private ArrayList<TravelDto> travelDtoArrayList;

    ArrayList<TicketDto> ticketDtoArrayList;
    Intent intentForCancelingTheTicket;


    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);

        viewPagerAdapter = new ViewPagerAdapter(
                getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

       // String ticketDate = "13/2/2021 12:12";
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy hh:mm", new Locale("tr"));
        String currentDate =sdf.format(new Date());
      /*  try {
            Date ticket = new SimpleDateFormat("d/M/yyyy hh:mm", Locale.ENGLISH)
                    .parse(ticketDate);
            Date current = new SimpleDateFormat("d/M/yyyy hh:mm", Locale.ENGLISH)
                    .parse(currentDate);


            if (ticket.compareTo(current) <= 0) {
                System.out.println("ticketDate is before currentDate");
            } else {
                System.out.println("Something weird happened...");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
*/
        // It is used to join TabLayout with ViewPager.
        tabLayout.setupWithViewPager(viewPager);

        myRef.collection("users")
                .document(mAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        userDto = (UserDto) task.getResult().toObject(UserDto.class);

                        ticketDtoArrayList = userDto.getTicketDtoArrayList();

                        for (int i = 0; i <userDto.getTicketDtoArrayList().size() ; i++) {
                            String ticketDate = userDto.getTicketDtoArrayList().get(i).getTravelDto().getDate()
                                    + " " +userDto.getTicketDtoArrayList().get(i).getTravelDto().getLeavingTime();

                                try {
                                    Date ticket = new SimpleDateFormat("d/M/yyyy hh:mm", Locale.ENGLISH)
                                            .parse(ticketDate);
                                    Date current = new SimpleDateFormat("d/M/yyyy hh:mm", Locale.ENGLISH)
                                            .parse(currentDate);

                                    if (ticket.compareTo(current) < 0) {

                                        if (userDto.getTicketDtoArrayList().get(i).getStatusOfTicket().equals("bought")){
                                            convertToPreviousTicket(userDto.getTicketDtoArrayList().get(i).getTravelId(),
                                                                    userDto.getTicketDtoArrayList().get(i).getTravelDto(),
                                                                    userDto.getTicketDtoArrayList().get(i));
                                        }
                                        else if (userDto.getTicketDtoArrayList().get(i).getStatusOfTicket().equals("favorite")||
                                                userDto.getTicketDtoArrayList().get(i).getStatusOfTicket().equals("reserved")){
                                            myRef.collection("users")
                                                    .document(mAuth.getCurrentUser().getUid())
                                                    .update("ticketDtoArrayList",
                                                            FieldValue.arrayRemove(userDto.getTicketDtoArrayList().get(i)));
                                        }
                                    }

                                }
                                catch (ParseException e) {
                                    e.printStackTrace();
                                }


                          }

                        }

                });



    }
    public void convertToPreviousTicket(String travelId, TravelDto travelDto, TicketDto ticketDto){
        TicketDto ticketDto2 = new TicketDto(travelId,"previous",travelDto);
        myRef.collection("users")
                .document(mAuth.getCurrentUser().getUid())
                .update("ticketDtoArrayList", FieldValue.arrayRemove(ticketDto)
                        ,"ticketDtoArrayList" , FieldValue.arrayUnion(ticketDto2));



        myRef.collection("users")
                .document(mAuth.getCurrentUser().getUid())
                .update("ticketDtoArrayList", FieldValue.arrayUnion(ticketDto2));
    }

}