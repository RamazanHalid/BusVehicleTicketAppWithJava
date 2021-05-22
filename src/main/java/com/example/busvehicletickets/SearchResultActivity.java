package com.example.busvehicletickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.busvehicletickets.dto.TravelDto;
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
    private ListView listView;

    private Intent intent2;
    private TravelDto travelDto;

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



        listView = (ListView) findViewById(R.id.search_resultList);
        intent2 = new Intent(this, SeatSelectingActivity.class);

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

                                travelDto = new TravelDto(doc.get("fromCity").toString(),
                                                          doc.get("toCity").toString(),
                                                          doc.get("distance").toString(),
                                                          doc.get("price").toString(),
                                                          doc.get("Date").toString(),
                                                          doc.get("travelTime").toString(),
                                                          doc.get("Time").toString());
                                intent2.putExtra("travelDetails" ,travelDto);
                                intent2.putExtra("travelDocumentId",document.getId());
                            }
                            mAdapter = new TravelDetailsAdapter(SearchResultActivity.this, travelDetailsArrayList);
                            listView.setAdapter(mAdapter);
                            listView.setOnItemClickListener(listClick);

                        } else {

                            System.out.println("*************************************************************");
                        }

                    }
                });





    }
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity(intent2);

        }
    };

}