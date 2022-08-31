package com.snail.adapterlist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.snail.adapterlist.R;
import com.snail.adapterlist.db.DBHelper;

public class DescriptionObjectActivity extends AppCompatActivity {

    public static final String KEY_ID     = "ID";
    public static final String KEY_NAME   = "NAME";
    public static final String KEY_AGE    = "AGE";
    public static final String KEY_LENGTH = "LENGTH";
    public static final String KEY_WEIGHT = "WEIGHT";
    public static final String KEY_COLOR  = "COLOR";

    private TextView textName;
    private TextView textAge;
    private TextView textLength;
    private TextView textWeight;
    private TextView textColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_object);

        textName   = findViewById(R.id.textViewAnimalNameDescriptionText);
        textAge    = findViewById(R.id.textViewAnimalAgeDescriptionText);
        textLength = findViewById(R.id.textViewAnimalLengthDescriptionText);
        textWeight = findViewById(R.id.textViewAnimalWeightDescriptionText);
        textColor  = findViewById(R.id.textViewAnimalColorDescriptionText);

        setDataDescription();

        Button bDeleteAnimal = findViewById(R.id.buttonRemoveAnimal);
        bDeleteAnimal.setOnClickListener(view -> DeleteAnimal());
    }

    private void setDataDescription() {
        Intent intent = getIntent();

        textName.setText(intent.getStringExtra(KEY_NAME));
        textAge.setText(String.valueOf(intent.getIntExtra(KEY_AGE, -1)));
        textLength.setText(String.valueOf(intent.getDoubleExtra(KEY_LENGTH, -1.0)));
        textWeight.setText(String.valueOf(intent.getDoubleExtra(KEY_WEIGHT, -1.0)));
        textColor.setText(intent.getStringExtra(KEY_COLOR));
    }

    private void DeleteAnimal() {
        DBHelper db = new DBHelper(this);
        db.deleteAnimal(getIntent().getLongExtra(KEY_ID, -1));
        finish();
    }
}