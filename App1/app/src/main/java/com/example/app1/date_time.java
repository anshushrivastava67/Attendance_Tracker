package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class date_time extends AppCompatActivity {

    Button buttondatentime;
    TextView timeanddate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_date_time);

        buttondatentime=findViewById(R.id.btn_time_date);
        timeanddate=findViewById(R.id.time_date);
        buttondatentime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tdate = (TextView) findViewById(R.id.time_date);
                long date = System.currentTimeMillis();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy\nhh-mm-ss a");
                String dateString = sdf.format(date);
                tdate.setText(dateString);
            }
        });

    }
}