package com.example.app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

//import android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Details extends AppCompatActivity {

//    private static final String ACCESS_FINE_LOCATION = ;
    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE = 100;
    private static final int PERMISSION_REQUEST_CODE = 123;

    //    private static final int PERMISSION_REQUEST_CODE = 1;

    private boolean isLocationPermissionGranted = false;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://attendance-tracker-8f963-default-rtdb.firebaseio.com/");

    TextView location,dt,name,school,employee_id;
    Button btn;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_details);
        location=findViewById(R.id.location);
        dt=findViewById(R.id.dateandtime);
        name=findViewById(R.id.name);
        school=findViewById(R.id.school);
        employee_id=findViewById(R.id.e_id);
        btn=findViewById(R.id.submit);
        getLastLocation();

        long date = System.currentTimeMillis();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy\nhh-mm-ss a");
        String dateString = sdf.format(date);
        dt.setText("Date and Time : "+dateString);
        databaseReference.child("login").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("set01")){
//                    final String  =snapshot.child("set01").child("password").getValue(String.class);
                    name.setText("Name : "+snapshot.child("set01").child("Name").getValue(String.class));
                    school.setText("School : "+snapshot.child("set01").child("school").getValue(String.class));
//                    employee_id.setText((String) snapshot.getValue(String.class));
//                    final String getschool=snapshot.child("set01").child("school").getValue(String.class);
//                    name.setText(getname);
//                    school.setText(getschool);
////                    employee_id.setText("set01");
////                    employee_id.setText();
//                    Toast.makeText(Details.this, "inside!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Details.this, date_time.class));
                finish();
            }
        });


    }


        @SuppressLint("SetTextI18n")
        private void getLastLocation(){
        if(ContextCompat.checkSelfPermission(Details.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {

//            Giving null values how solve later on
            if (fusedLocationProviderClient == null) {
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            }

           fusedLocationProviderClient.getLastLocation()
    .addOnSuccessListener(location1 -> {
        if (location1 != null) {
            Geocoder geocoder = new Geocoder(Details.this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 1);
                location.setText("Address : "+addresses.get(0).getAddressLine(0));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
//            Toast.makeText(this, "hi!", Toast.LENGTH_SHORT).show();
        }else{
            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(Details.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }
            else{
                Toast.makeText(this, "PLease Provide the Required Permission!!!", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
