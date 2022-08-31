package com.snail.adapterlist.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.snail.adapterlist.R;
import com.snail.adapterlist.adapters.CustomAdapter;
import com.snail.adapterlist.db.DBHelper;
import com.snail.adapterlist.objects.Animal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DBHelper db;

    private CustomAdapter     recyclerAdapter;
    private final ArrayList<Animal> animalsList = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        db.FillListAnimal(animalsList);

        RecyclerView recycler = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recyclerAdapter = new CustomAdapter(animalsList, this);
        recycler.setAdapter(recyclerAdapter);

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddingActivity.class);
            startActivity(intent);
        });

        recyclerAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();
        db.FillListAnimal(animalsList);
        recyclerAdapter.notifyDataSetChanged();
    }
}