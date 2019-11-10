package com.example.reds0n.eventticketqr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    public EditText email,password;
    public Button loginButton;
    public FirebaseDatabase database;
    public DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.button);


         database = FirebaseDatabase.getInstance();
         myRef = database.getReference("User");


            loginButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {


                      myRef.addValueEventListener(new ValueEventListener() {
                          @Override
                          public void onDataChange(DataSnapshot dataSnapshot) {

                             if(dataSnapshot.child(email.getText().toString()).exists()) {
                                 User users = dataSnapshot.child(email.getText().toString()).getValue(User.class);
                                 Common.currentuser = users;

                                 if (email.getText().toString().equals(users.getEmail())) {

                                     if (users.getPassword().equals(password.getText().toString())) {

                                         Intent home = new Intent(Login.this, Event.class);
                                         startActivity(home);

                                     } else {
                                         Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                     }


                                 } else {

                                     Toast.makeText(Login.this, "User does not exist !!", Toast.LENGTH_SHORT).show();
                                 }
                             }
                             else
                             Toast.makeText(Login.this, "User does not exist !!", Toast.LENGTH_SHORT).show();
                          }

                          @Override
                          public void onCancelled(DatabaseError databaseError) {

                          }
                      });
                      }
            });




    }







}
