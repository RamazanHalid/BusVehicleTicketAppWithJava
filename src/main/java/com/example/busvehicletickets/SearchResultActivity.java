package com.example.busvehicletickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchResultActivity extends AppCompatActivity {
    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    private static final String TAG = "SearchResultActivity";
    private static ArrayList<TravelDetails> travelDetailsArrayList;
    private static List<String> results;
    private TravelDetailsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        String dateTravel = "";
        String fromCity = "";
        String toCity = "";

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        dateTravel = (String) bundle.get("travelDate");
        fromCity = (String) bundle.get("fromCity");
        toCity = (String) bundle.get("toCity");

        TextView fromToCityTextView = (TextView) findViewById(R.id.search_FromAndToCities);
        fromToCityTextView.setText(fromCity.toUpperCase() + " ---> " + toCity.toUpperCase());

        TextView dateTravelTextVIew = (TextView) findViewById(R.id.search_travelDate);
        dateTravelTextVIew.setText(dateTravel);


        ListView listView = (ListView) findViewById(R.id.search_resultList);

        myRef.collection("travels")
                .orderBy("Time", Query.Direction.ASCENDING)
                .whereEqualTo("fromCity", fromCity)
                .whereEqualTo("toCity", toCity)
                .whereEqualTo("Date", dateTravel)

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            travelDetailsArrayList = new ArrayList<TravelDetails>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println("************" + document.getData() + "*****************");


                                Map<String, Object> doc = document.getData();

                                travelDetailsArrayList.add(new TravelDetails(doc.get("distance").toString() +"km",
                                        doc.get("price") + "TL", doc.get("travelTime") + "h", doc.get("Time").toString()));


                            }
                            mAdapter = new TravelDetailsAdapter(SearchResultActivity.this, travelDetailsArrayList);
                            listView.setAdapter(mAdapter);
                        } else {

                            System.out.println("*************************************************************");
                        }

                    }
                });





    }
}