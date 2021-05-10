package com.example.busvehicletickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.busvehicletickets.dto.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.events.Event;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class HomeFragment extends Fragment{

    String[] contents1 = {"Announcements" , "News"};
    FirebaseFirestore myRef = FirebaseFirestore.getInstance();
    private View view;
    private User user;


    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {




         view = inflater.inflate(R.layout.home_frag_layout, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.my_list , contents1);
        ListView listView = (ListView) view.findViewById(R.id.contents);
        listView.setAdapter(adapter);
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
                        textView.setText(nameSurname);


                    }
                });


        //System.out.println(user.phoneNumber);
        return view;
    }




    @Override
    public void onStart() {
        super.onStart();

    }
}