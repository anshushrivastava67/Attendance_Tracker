package com.example.app1;

import android.Manifest;
//import android.annotation.SuppressLint;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;

//import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


//import android.content.IntentSender;
//import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
//import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
//import android.view.View;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;

//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationSettingsRequest;
//import com.google.android.gms.location.LocationSettingsResponse;
//import com.google.android.gms.location.LocationSettingsStatusCodes;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.gun0912.tedpermission.PermissionBuilder;
//import com.gun0912.tedpermission.PermissionListener;
//import com.gun0912.tedpermission.normal.TedPermission;

import java.io.IOException;
//import java.text.BreakIterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE = 100;
    private static final int PERMISSION_REQUEST_CODE = 123;

    //    private static final int PERMISSION_REQUEST_CODE = 1;
    private boolean isLocationPermissionGranted = false;
    //    int REQUEST_LOCATION = 88;
    private TextView textview;
    private EditText editText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        checkLocationPermission();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//        getLastLocation();
        Button button = findViewById(R.id.button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btn_arrow = findViewById(R.id.arrow);
        textview = findViewById(R.id.textView);
        editText = findViewById(R.id.edittext);
        button.setOnClickListener(v -> {
//                Toast.makeText(MainActivity.this, "Hi Click Listener Worked!!", Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, "Hello!!!", Toast.LENGTH_SHORT).show();
            String s = editText.getText().toString();
            int kg = Integer.parseInt(s);
            double pound = 2.205 * kg;
            textview.setText("The corresponding value in Pound is: " + pound);
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                startActivity(new Intent(MainActivity.this, Camera.class));
                finish();
            }, 3000);

        });
        btn_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login_Page.class));
                finish();
            }
        });

    }
//    private void getLastLocation(){
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
//        {
//
////            Giving null values how solve later on
//
//           fusedLocationProviderClient.getLastLocation()
//    .addOnSuccessListener(location1 -> {
//        if (location1 != null) {
//            Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
//            try {
//                List<Address> addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 1);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    });
////            Toast.makeText(this, "hi!", Toast.LENGTH_SHORT).show();
//        }else{
//            askPermission();
//        }
//    }
//
//    private void askPermission() {
//        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if(requestCode == REQUEST_CODE){
//            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                getLastLocation();
//            }
//            else{
//                Toast.makeText(MainActivity.this, "PLease provide the required permission", Toast.LENGTH_SHORT).show();
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//}

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted
            isLocationPermissionGranted = true;
        } else {
            // Permission is not granted
            isLocationPermissionGranted = false;
            // Request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                isLocationPermissionGranted = true;
            } else {
                // Permission is not granted
                isLocationPermissionGranted = false;
                // Request the permission again
                checkLocationPermission();
            }
        }
    }
}
