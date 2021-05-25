/**
 * @author Ramazan Halid 20160807009
 * @version 05.04.2021
 *
 * ToDo
 * This is a Bus Vehicle Tickets Android Application.
 *
 *
 * */


package com.example.busvehicletickets.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.busvehicletickets.R;
import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.UserDto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
   static  FirebaseAuth mAuth;
   static  FirebaseDatabase mDatabase;
   static  FirebaseFirestore myRef;
   static  String email;
   static  String password;
   static  String confirmPassword;
   static  String personNameSurname;
   static  String phoneNumber;
   static  String userGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



    }
        public void goLogin (View view){
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
        public void createUser(View view) {
            mDatabase = FirebaseDatabase.getInstance();
            mAuth = FirebaseAuth.getInstance();
            myRef = FirebaseFirestore.getInstance();

            EditText editTextEmail = (EditText) findViewById(R.id.register_editTextEmail);
            email = editTextEmail.getText().toString();

            email = email.replaceAll("\\s+", "");

            EditText editTextPassword = (EditText) findViewById(R.id.register_editTextPassword);
            password = editTextPassword.getText().toString();

            password = password.replaceAll("\\s+", "");

            EditText editTextConfirmPassword = (EditText) findViewById(R.id.register_editTextConfirmPassword);
            confirmPassword = editTextConfirmPassword.getText().toString();

            confirmPassword = confirmPassword.replaceAll("\\s+", "");

            EditText editTextPersonNameSurname = (EditText) findViewById(R.id.register_editTextTextPersonName);
            personNameSurname = editTextPersonNameSurname.getText().toString();

            personNameSurname = personNameSurname.replaceAll("\\s+", "");

            EditText editTextPhoneNumber = (EditText) findViewById(R.id.register_editTextPhone);
            phoneNumber = editTextPhoneNumber.getText().toString();

            phoneNumber = phoneNumber.replaceAll("\\s+", "");

            RadioGroup radioGroupGenders = (RadioGroup) findViewById(R.id.register_radioGander);
            int selectedId = radioGroupGenders.getCheckedRadioButtonId();
            RadioButton radioButtonGender = (RadioButton) findViewById(selectedId);
            userGender = (String) radioButtonGender.getText();

            userGender = userGender.replaceAll("\\s+", "");


            if (!(email.equals("") ||
                    password.equals("") ||
                    confirmPassword.equals("") ||
                    personNameSurname.equals("") ||
                    phoneNumber.equals("") ||
                    userGender.equals("")
            )) {
                ArrayList<TicketDto> ticketDtos = new ArrayList<>();
                UserDto userDto = new UserDto(personNameSurname, phoneNumber, userGender,ticketDtos);

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Password and Confirm password must be the same", Toast.LENGTH_SHORT).show();
                } else {

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String userId = mAuth.getUid();
                                myRef.collection("users")
                                        .document(userId)
                                        .set(userDto);


                                Toast.makeText(RegisterActivity.this, "Account  created successfully!", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            } else {

                Toast.makeText(RegisterActivity.this, "Please FILL all of blanks!", Toast.LENGTH_SHORT).show();

            }
        }
    }
