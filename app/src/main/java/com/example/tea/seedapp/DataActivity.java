package com.example.tea.seedapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        ImageView imageView=(ImageView) findViewById(R.id.imageView2);
        TextView textView=(TextView) findViewById(R.id.textView);
        textView.setText(getIntent().getStringExtra("rastlina"));
        imageView.setImageResource(getIntent().getIntExtra("slika",R.drawable.tomato_item));
       // imageView.setImageResource(R.drawable.tomato_item);
    }
}
