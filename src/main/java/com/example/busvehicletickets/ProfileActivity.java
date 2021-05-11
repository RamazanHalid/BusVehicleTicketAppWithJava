package com.example.busvehicletickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.UpdateDocumentRequest;

public class ProfileActivity extends AppCompatActivity {


    private String nameSurname;
    private String eMail;
    private String phone;
    private String password;
    private String password2;
    EditText editTextNameSurname;
    EditText editTextPhone;
    String userId;
    EditText editTextEMail;
    FirebaseUser firebaseUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        eMail = firebaseUser.getEmail();

        editTextEMail = (EditText) findViewById(R.id.profile_EmailAddress);
        editTextEMail.setText(eMail);

        userId = firebaseUser.getUid();

        DocumentReference docRef = db.collection("users").document(userId);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    nameSurname = document.get("nameSurname").toString();
                    phone = document.get("phoneNumber").toString();

                    editTextNameSurname = (EditText) findViewById(R.id.profile_nameSurname);
                    editTextNameSurname.setText(nameSurname);

                    editTextPhone = (EditText) findViewById(R.id.profile_Phone);
                    editTextPhone.setText(phone);



                } else {
                    System.out.println("Profile activitittttttt");
                }
            }
        });

        //UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder();

    }
    public void updateInfo(View view){

        EditText editTextPassword = (EditText) findViewById(R.id.profile_password);
        password = editTextPassword.getText().toString();


        password = password.replaceAll("\\s+" , "");

        EditText editTextPassword2 = (EditText) findViewById(R.id.profile_password2);
        password2 = editTextPassword2.getText().toString();

        password2 = password2.replaceAll("\\s+" , "");

        editTextEMail = (EditText) findViewById(R.id.profile_EmailAddress);
        eMail = editTextEMail.getText().toString();

        eMail = eMail.replaceAll("\\s+" , "");

        editTextNameSurname = (EditText) findViewById(R.id.profile_nameSurname);
        nameSurname = editTextNameSurname.getText().toString();

        nameSurname = nameSurname.replaceAll("\\s+" , "");

        editTextPhone = (EditText) findViewById(R.id.profile_Phone);
        phone = editTextPhone.getText().toString();

        phone = phone.replaceAll("\\s+" , "");

        if (!(password=="" || eMail=="" || password2=="" || nameSurname=="" || phone=="")){
            if (password.equals(password2)){
                db.collection("users")
                        .document(userId)
                        .update("nameSurname", nameSurname , "phoneNumber", phone);

                firebaseUser.updateEmail(eMail);
                firebaseUser.updatePassword(password);
            }
            else {
                Toast.makeText(ProfileActivity.this, "Passwords are not matching!!!", Toast.LENGTH_SHORT).show();
            }
        }

    }
}