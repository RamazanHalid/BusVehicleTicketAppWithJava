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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseFirestore myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         mAuth = FirebaseAuth.getInstance();
         myRef = FirebaseFirestore.getInstance();
        Intent mainIntent2 = new Intent(this, ChairSelectActivity.class);
        startActivity(mainIntent2);
    }
    @Override
    protected void onStart() {

        super.onStart();
       /* if (mAuth.getCurrentUser()!=null){
            Intent mainIntent = new Intent(this, MainActivity2.class);
            startActivity(mainIntent);

          }*/
    }

    public void navigateUser(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    public void login(View view){
        EditText editTextEmail = (EditText) findViewById(R.id.login_editTextEmail);
        String email = editTextEmail.getText().toString();

        email = email.replaceAll("\\s+" , "");

        EditText editTextPassword = (EditText) findViewById(R.id.login_editTextPassword);
        String password = editTextPassword.getText().toString();

        password = password.replaceAll("\\s+" , "");

        if (!(email.equals("") || password.equals("") )){
            Intent mainIntent = new Intent(this, MainActivity2.class);
            mainIntent.putExtra("userEmail" , email);
            FirebaseUser user = mAuth.getCurrentUser();
            mainIntent.putExtra("userId", mAuth.getUid());
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        startActivity(mainIntent);

                    }
                    else{
                        Toast.makeText(LoginActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
        else {

            Toast.makeText(LoginActivity.this,"Email Or Password can not be EMPTY!", Toast.LENGTH_SHORT).show();
        }


    }
}