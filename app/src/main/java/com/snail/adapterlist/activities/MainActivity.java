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

/**
 * Launch activity of application
 */
public class MainActivity extends AppCompatActivity {

    /** Work with Sqlite */
    private DBHelper db;

    /** Custom adapter to display in recyclerView */
    private CustomAdapter     recyclerAdapter;
    /** ArrayList of animals which display in recyclerView */
    private final ArrayList<Animal> animalsList = new ArrayList<>();

    /** onCreate method of MainActivity
     *
     * @param savedInstanceState a reference to a Bundle object that is passed into the onCreate method of every Android Activity
     */
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

    /** onResume method of MainActivity
     *
     */
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();
        db.FillListAnimal(animalsList);
        recyclerAdapter.notifyDataSetChanged();
    }
}