package com.example.app1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Camera extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 100 ;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://attendance-tracker-8f963-default-rtdb.firebaseio.com/");

    private ImageView imageView;
    private Button button,btn_present,btn_absent,btn_arrow;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_camera);

        imageView=findViewById(R.id.capturedImage);
        button=findViewById(R.id.openCamera);
        btn_present=findViewById(R.id.present);
        btn_absent=findViewById(R.id.absent);
        btn_arrow=findViewById(R.id.arrow);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(open_camera,100);
            }
        });

        btn_present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                long date = System.currentTimeMillis();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy\nhh-mm-ss a");
                String dateString = sdf.format(date);
                databaseReference.child("login").child("Manav").push().child("In").child("Date and time").setValue(dateString);
                Handler handler = new Handler();
                Toast.makeText(Camera.this, "Your 'In' Status is Updated in the excel!!!", Toast.LENGTH_SHORT).show();
            }
        });
        btn_absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long date = System.currentTimeMillis();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy\nhh-mm-ss a");
                String dateString = sdf.format(date);
                databaseReference.child("login").child("Manav").push().child("Out").child("Date and time").setValue(dateString);
                Handler handler = new Handler();
                Toast.makeText(Camera.this, "Your 'Out' status is updated to the excel!!!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Camera.this, Login_Page.class));
                finish();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] photoData = baos.toByteArray();

            String photoString = Base64.encodeToString(photoData, Base64.DEFAULT);

            databaseReference.child("login").child("Manav").child("Photo").setValue(photoString);

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                startActivity(new Intent(Camera.this, Details.class));
                finish();
            }, 3000);
        }
    }








}