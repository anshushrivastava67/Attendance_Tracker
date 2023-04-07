package com.example.app1;

//import androidx.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
//import com.google.firebase.database.ValueEventListener;
//
//import org.w3c.dom.Text;

//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.Objects;

public class Login_Page extends AppCompatActivity {
//Connection connection;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://attendance-tracker-8f963-default-rtdb.firebaseio.com/");

        DatabaseReference reff;
//    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_login_page);

        TextView username =(TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.login_btn);

        String username2=username.getText().toString();
        String password2=password.getText().toString();

        loginbtn.setOnClickListener(v -> {

//            if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
//                Toast.makeText(Login_Page.this, "Successfully Logged", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(Login_Page.this,MainActivity.class));
//                finish();
//            }
//            else{
//                Toast.makeText(Login_Page.this, "Invalid Details!!!", Toast.LENGTH_SHORT).show();
//            }


//                inserting the data


//                databaseReference.child("login").child("Manav").child("password").setValue("12345");
////                reff.push();
//                Toast.makeText(Login_Page.this, "Data Inserted Successfully!!", Toast.LENGTH_SHORT).show();

//                  Checking the data with firebase
                if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(Login_Page.this, "Please enter Email Id or Password", Toast.LENGTH_SHORT).show();
                }

                else{

                    databaseReference.child("login").addValueEventListener(new ValueEventListener() {
                        @Override

                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(username.getText().toString())){
                                final String getPassword =snapshot.child(username.getText().toString()).child("password").getValue(String.class);

                                if(getPassword.equals(password.getText().toString())){
                                    Toast.makeText(Login_Page.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login_Page.this,Camera.class));
                                    finish();
                                }
                                else{

                                    Toast.makeText(Login_Page.this, "Wrong Password!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
//                                Toast.makeText(Login_Page.this, "hello!!", Toast.LENGTH_SHORT).show();
                                Toast.makeText(Login_Page.this, "Username Does not exist!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Login_Page.this, "Error!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

//                reff.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                })


        });
    }
}