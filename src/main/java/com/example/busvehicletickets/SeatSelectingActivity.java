package com.example.busvehicletickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.busvehicletickets.dto.TravelDto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SeatSelectingActivity extends AppCompatActivity implements View.OnClickListener {
    ViewGroup layout;
    private Intent intent;
    private Intent intentFromSearchToResult;
    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    private String selectedDocumentIdFromSearchResultActivity;


    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 100;
    int seatGaping = 10;

    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 2;
    int STATUS_RESERVED = 3;
    String selectedIds = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selecting);
        layout = findViewById(R.id.layoutSeat);
        intentFromSearchToResult = getIntent();
        selectedDocumentIdFromSearchResultActivity = intentFromSearchToResult.getStringExtra("travelDocumentId");

        myRef.collection("travels")
                .document(selectedDocumentIdFromSearchResultActivity)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                ArrayList<String> list = (ArrayList<String>) document.get("seats");
                                String result = list.toString();
                                result = result.replaceAll("\\[", "")
                                .replaceAll("\\]", "")
                                .replaceAll("[{}]","")
                                .replaceAll(" ", "");
                                 String[] result2 = result.split(",");
                                HashMap<Integer, String> map2 = new HashMap<>();

                                for (String q: result2 ) {
                                    String[] result3 = q.split("=");
                                    int numberOfSeatInFirestore = Integer.parseInt(result3[0]);
                                    if (result3[1].equals("reserved"))
                                        map2.put(numberOfSeatInFirestore, "R");
                                    else if (result3[1].equals("booked"))
                                        map2.put(numberOfSeatInFirestore, "U");
                                    else if (result3[1].equals("available"))
                                        map2.put(numberOfSeatInFirestore, "A");
                                    else
                                        System.out.println("There is a problem in AUR");
                                }
                                String seatDesign ="_________________/";
                                for (int i = 1; i < map2.size() + 1 ; i++) {

                                    if (i%2 ==0){
                                        if (i%4==0)
                                            seatDesign += map2.get(i) + "/";
                                        else
                                            seatDesign += map2.get(i) + "__";

                                    }
                                    else {
                                        seatDesign += map2.get(i);
                                    }
                                }

                                meth(seatDesign);

                            }
                        }
                    }
                });



    }
    @Override
    public void onClick(View view) {
        if ((int) view.getTag() == STATUS_AVAILABLE) {
            String seatNumber = new String();
            seatNumber = String.valueOf(view.getId()) ;
            System.out.println(seatNumber);
            intent.putExtra("seatNumber", seatNumber);
            intent.putExtra("travelDocumentId", selectedDocumentIdFromSearchResultActivity);
            startActivity(intent);


        } else if ((int) view.getTag() == STATUS_BOOKED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Booked", Toast.LENGTH_SHORT).show();
        } else if ((int) view.getTag() == STATUS_RESERVED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
        }
    }
    public void meth(String seats){
        intent = new Intent(this, ResultActivity.class);

        TravelDto travelDto2= (TravelDto) intentFromSearchToResult.getSerializableExtra("travelDetails");
        intent.putExtra("travelDetails2", travelDto2);

        seats = "/" + seats;

        LinearLayout layoutSeat = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
        layout.addView(layoutSeat);

        LinearLayout layout = null;

        int count = 0;

        for (int index = 0; index < seats.length(); index++) {
            if (seats.charAt(index) == '/') {
                layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutSeat.addView(layout);
            } else if (seats.charAt(index) == 'U') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_booked);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_BOOKED);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'A') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_book);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.BLACK);
                view.setTag(STATUS_AVAILABLE);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'R') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_reserved);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_RESERVED);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == '_') {
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setBackgroundColor(Color.TRANSPARENT);
                view.setText("");
                layout.addView(view);
            }
        }
    }
}