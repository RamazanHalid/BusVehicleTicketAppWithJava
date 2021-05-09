/**
 * @author Ramazan Halid 20160807009
 * @version 05.04.2021
 *
 * ToDo
 * This is a Bus Vehicle Tickets Android Application.
 *
 *
 * */


package com.example.busvehicletickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.busvehicletickets.dto.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {
     FirebaseAuth mAuth;
     FirebaseDatabase mDatabase;
     FirebaseFirestore myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseFirestore.getInstance();


    }

    public void createUser(View view){
        EditText editTextEmail = (EditText) findViewById(R.id.register_editTextEmail);
        String email = editTextEmail.getText().toString();

        EditText editTextPassword = (EditText) findViewById(R.id.register_editTextPassword);
        String password = editTextPassword.getText().toString();

        EditText editTextConfirmPassword = (EditText) findViewById(R.id.register_editTextConfirmPassword);
        String confirmPassword = editTextConfirmPassword.getText().toString();

        EditText editTextPersonNameSurname = (EditText) findViewById(R.id.register_editTextTextPersonName);
        String personNameSurname = editTextPersonNameSurname.getText().toString();

        EditText editTextPhoneNumber = (EditText) findViewById(R.id.register_editTextPhone);
        String phoneNumber = editTextPhoneNumber.getText().toString();

        User user = new User(personNameSurname,phoneNumber,"male");

        if (!password.equals(confirmPassword)){
            Toast.makeText(RegisterActivity.this,"Password and Confirm password must be the same",Toast.LENGTH_SHORT).show();
          }
        else{

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()){
                             myRef.collection("users")
                                     .add(user)
                                     .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                         @Override
                                         public void onSuccess(DocumentReference documentReference) {

                                         }
                                     });

                             Toast.makeText(RegisterActivity.this, "Account  created successfully!", Toast.LENGTH_SHORT).show();

                         }
                         else{
                             Toast.makeText(RegisterActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                         }
                }
            });
        }
    }

    public void goLogin(View view) {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }
    public void writeNewUser(String userId, String nameSurname, String phoneNumber){


    }
}