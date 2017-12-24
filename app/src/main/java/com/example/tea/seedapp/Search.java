package com.example.tea.seedapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.widget.Toast;

import com.example.tea.seedapp.Web.GetDataAsync;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity implements GetDataAsync.DataListener {

    List<DataItem> lstData;
    CostumAdapter adapter;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lstData = new ArrayList<>();
        lstData.add(new DataItem(R.drawable.tomato_item,"TEA in DRAGAN"));

        ListView listView=(ListView)findViewById(R.id.listView);
        adapter= new CostumAdapter(this,R.layout.itemrow,lstData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent intent=new Intent();
                intent.putExtra("rastlina",lstData.get(position).plantTxt);
                intent.putExtra("slika",lstData.get(position).idItem);
                intent.setClass(Search.this,DataActivity.class);
                startActivity(intent);

            }
        });

        mTextMessage = (TextView) findViewById(R.id.TVplant);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(!isNetworkAvailable()) {
            ShowToast();
        }
        else {
            new GetDataAsync(this).execute("https://jsonplaceholder.typicode.com/posts");
        }
        }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void ShowToast() {
        Toast.makeText(this, "No network connection", Toast.LENGTH_LONG).show();
    }


    @Override
    public void dataReceived(JSONArray data) {
        if(data==null){

            return;
        }
        for(int i = 0; i<data.length();i++){
            try {
                JSONObject jsonO=data.getJSONObject(i);
                String title = jsonO.getString("title");
                lstData.add(new DataItem(R.drawable.tomato_item,title));
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {

                e.printStackTrace();
            }

        }
    }
}



