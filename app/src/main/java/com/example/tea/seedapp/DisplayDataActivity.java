package com.example.tea.seedapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.tea.seedapp.Web.GetDataAsync;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class DisplayDataActivity extends AppCompatActivity implements GetDataAsync.DataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        tr = (TextView) findViewById(R.id.TVplant);
        new GetDataAsync(this).execute("https://jsonplaceholder.typicode.com/posts/1");
    }
    TextView tr;

    @Override
    public void dataReceived(JSONArray data) {
        tr.setText(data.toString());
    }
}
