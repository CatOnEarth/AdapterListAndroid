package com.snail.adapterlist.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.snail.adapterlist.R;
import com.snail.adapterlist.adapters.CustomAdapter;
import com.snail.adapterlist.db.DBHelper;
import com.snail.adapterlist.objects.Animal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper db;

    private CustomAdapter adapter;
    private ArrayList<Animal> animalsList = new ArrayList<>();
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        db.FillListAnimal(animalsList);

        recycler = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        adapter = new CustomAdapter(animalsList, this);
        recycler.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddingActivity.class);
            startActivity(intent);
        });

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}