package com.example.busvehicletickets;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.busvehicletickets.dto.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.type.Date;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment{

    private static final String TAG = "HomeFragment";
    String[] contents1 = {"Announcements" , "News"};
    String date;

    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    private View view;
    private User user;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private TextView travelDate;
    private String toCity;
    private String fromCity;
    private DatePickerDialog.OnDateSetListener dataSetListener;


    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.home_frag_layout, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.my_list , contents1);

        String userId = getActivity().getIntent().getExtras().getString("userId");
        TextView textView = (TextView) view.findViewById(R.id.homepage_textViewUser);
        myRef.collection("users")
                .document(userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        String nameSurname =task.getResult().get("nameSurname").toString();
                        String phoneNumber =task.getResult().get("phoneNumber").toString();
                        String gender =task.getResult().get("gender").toString();
                        user = new User(nameSurname,phoneNumber,gender);
                        textView.setText("Welcome \n" + nameSurname);

                        System.out.println("***********************");
                        spinnerFrom = view.findViewById(R.id.home_spinnerFrom);
                        spinnerTo = view.findViewById(R.id.home_spinnerTo);


                        List<String> cities = new ArrayList<>();
                        cities.add("izmir");
                        cities.add("istanbul");
                        cities.add("antalya");


                        //style and populate the spinner
                        ArrayAdapter<String> dataAdapter;
                        dataAdapter = new ArrayAdapter(getContext(),R.layout.my_list, cities);

                        //Dropdown layout style
                        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                        //attaching data adapter to spinner
                        spinnerFrom.setAdapter(dataAdapter);
                        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                //on selecting a spinner
                                fromCity = parent.getItemAtPosition(position).toString();


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        spinnerTo.setAdapter(dataAdapter);
                        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                toCity = parent.getItemAtPosition(position).toString();
                                                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        travelDate = (TextView) view.findViewById(R.id.travelDate);
                        travelDate.setText( "12/05/2021");
                        travelDate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Calendar cal = Calendar.getInstance();


                                int year = cal.get(Calendar.YEAR);
                                int month = cal.get(Calendar.MONTH);
                                int day = cal.get(Calendar.DAY_OF_MONTH);
                                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                                        R.style.Theme_MaterialComponents_DayNight_Dialog_MinWidth_Bridge,
                                        dataSetListener,
                                        day,month,year);
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GREEN));
                                dialog.show();
                            }
                        });
                        dataSetListener  = new DatePickerDialog.OnDateSetListener(){

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Log.d(TAG,"OnDateSet: date: " + dayOfMonth + "/" + month + "/"  + year);
                                date =  dayOfMonth + "/" + month + "/"  + year;
                                travelDate.setText(date);
                            }
                        };


                        Button searchButton = (Button) view.findViewById(R.id.home_searchButton);
                        searchButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (travelDate ==null){
                                    Toast.makeText(getContext(), "Travel date can not be EMPTY!", Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    Intent intent = new Intent(getContext(), SearchResultActivity.class);
                                    String travelDateString = travelDate.getText().toString();

                                    intent.putExtra("travelDate", travelDateString);
                                    intent.putExtra("toCity", toCity);
                                    intent.putExtra("fromCity", fromCity);
                                    startActivity(intent);

                                }

                            }
                        });
                    }
                });




        return view;
    }




    @Override
    public void onStart() {
        super.onStart();

    }
}