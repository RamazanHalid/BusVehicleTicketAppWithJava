package com.example.busvehicletickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    String[] contents1 = {"Announcements" , "News"};
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private View view;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



         view = inflater.inflate(R.layout.home_frag_layout, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.my_list , contents1);
        ListView listView = (ListView) view.findViewById(R.id.contents);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        /*mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()){
                           User user = task.getResult().getValue(User.class);
                           TextView textViewUser = (TextView) view.findViewById(R.id.homepage_textViewUser);
                           textViewUser.setText("Welcome " + user.nameSurname );
                        }
                        else {
                            System.out.println(task.getException().getMessage());
                        }
                    }
                });*/

    }
}