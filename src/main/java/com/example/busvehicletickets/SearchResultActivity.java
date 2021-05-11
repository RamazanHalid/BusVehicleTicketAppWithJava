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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchResultActivity extends AppCompatActivity {
    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    private static final String TAG = "SearchResultActivity";
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

        ArrayList<TravelDetails> travelDetailsArrayList = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.search_resultList);

        travelDetailsArrayList.add(new TravelDetails("123", "11", "333", "1000"));
        travelDetailsArrayList.add(new TravelDetails("1223", "121", "3333", "1000"));
        travelDetailsArrayList.add(new TravelDetails("13423", "11", "33433", "1000"));
        travelDetailsArrayList.add(new TravelDetails("12343", "1211", "333", "10001"));
        myRef.collection("travels")
                .whereEqualTo("fromCity", fromCity)
                .whereEqualTo("toCity", toCity)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println("************" + document.getData() + "*****************");

                                results = new ArrayList<>();
                                Map<String, Object> doc = document.getData();
                                String rowResult = doc.get("Time").toString() + "  |   " + doc.get("distance").toString() + "km |   "
                                        + doc.get("travelTime") + "h |   " + doc.get("price") + "TL";

                                results.add(rowResult);


                            }
                        } else {

                            System.out.println("*************************************************************");
                        }
                    }
                });



        /*ArrayAdapter<String> resultsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results);
        */
        mAdapter = new TravelDetailsAdapter(this, travelDetailsArrayList);

        listView.setAdapter(mAdapter);
    }
}