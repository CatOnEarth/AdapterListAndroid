package com.snail.adapterlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DescriptionObject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_oblect);

        Intent intent = getIntent();

        TextView textView = findViewById(R.id.textView);
        textView.setText(intent.getStringExtra("NAME"));
    }
}