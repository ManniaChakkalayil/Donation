package com.example.donation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;


public class donationreport extends AppCompatActivity {
    //defining array list variable
    ArrayList<donation> ArrayList;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager LayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donationreport);

        recyclerView = findViewById(R.id.RecyclerView);

        Bundle b = this.getIntent().getExtras();
        ArrayList = b.getParcelableArrayList("myKey");


        // use layoutManager
        LayoutManager = new LinearLayoutManager(this);

        //specify adapter
        myAdapter = new MyAdapter(ArrayList);

        //set layout manager adn adapter
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(myAdapter);
    }
}

