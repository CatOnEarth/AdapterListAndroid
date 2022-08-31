package com.snail.adapterlist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.snail.adapterlist.R;
import com.snail.adapterlist.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> animals;
    private DBHelper db;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        animals = new ArrayList<>();

        db.FillListAnimal(animals);

        ListView listview = findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, animals);

        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, DescriptionObjectActivity.class);
            startActivity(intent);
        });

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddingActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        db.FillListAnimal(animals);
        arrayAdapter.notifyDataSetChanged();
    }
}