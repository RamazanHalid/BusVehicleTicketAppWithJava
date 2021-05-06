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
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.busvehicletickets.dto.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database =FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

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

        User userInfo = new User(personNameSurname,phoneNumber);

        if (!password.equals(confirmPassword)){
            Toast.makeText(RegisterActivity.this,"Password and Confirm password must be the same",Toast.LENGTH_SHORT).show();
          }
        else{

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()){
                             FirebaseUser user = mAuth.getCurrentUser();
                           /*  mDatabase = FirebaseDatabase.getInstance().getReference();
                             mDatabase.child("users").child(user.getUid()).setValue(userInfo);
*/
                             System.out.println(user.getEmail());

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
}