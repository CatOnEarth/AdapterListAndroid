package com.snail.adapterlist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.snail.adapterlist.R;
import com.snail.adapterlist.db.DBHelper;
import com.snail.adapterlist.objects.Animal;

public class AddingActivity extends AppCompatActivity {

    private EditText editTextNameAnimal;
    private EditText editTextAgeAnimal;
    private EditText editTextLengthAnimal;
    private EditText editTextWeightAnimal;
    private EditText editTextColorAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        editTextNameAnimal   = findViewById(R.id.editTextTextNameAnimal);
        editTextAgeAnimal    = findViewById(R.id.editTextTextAgeAnimal);
        editTextLengthAnimal = findViewById(R.id.editTextTextLengthAnimal);
        editTextWeightAnimal = findViewById(R.id.editTextTextWeightAnimal);
        editTextColorAnimal  = findViewById(R.id.editTextTexColorAnimal);

        Button bSaveAnimal = findViewById(R.id.buttonSaveAnimal);
        bSaveAnimal.setOnClickListener(view -> SaveAnimalInfo());
    }

    private void SaveAnimalInfo() {
        if (IsCorrectInfo()) {
            SaveAnimal();
        }
    }

    private void SaveAnimal() {
        String name   = editTextNameAnimal.getText().toString();
        int    age    = Integer.parseInt(editTextAgeAnimal.getText().toString());
        double length = Double.parseDouble(editTextLengthAnimal.getText().toString());
        double weight = Double.parseDouble(editTextWeightAnimal.getText().toString());
        String color  = editTextColorAnimal.getText().toString();

        DBHelper db = new DBHelper(this);
        Animal animal = new Animal(name, age, length, weight, color);
        if (db.IsAnimalExist(animal)) {
            Toast.makeText(this, "Животное уже существует", Toast.LENGTH_LONG).show();
        } else {
            db.insertAnimal(animal);
        }
    }

    private boolean IsCorrectInfo() {
        return IsCorrectName() && IsCorrectColor() && IsCorrectWeight()
                     && IsCorrectLength() && IsCorrectAge();
    }

    private boolean IsCorrectLength() {
        String textLength = editTextLengthAnimal.getText().toString();
        try {
            Double.parseDouble(textLength);
        } catch (NumberFormatException e) {
            editTextLengthAnimal.setError("Укажите длину");
            return false;
        }

        return true;
    }

    private boolean IsCorrectColor() {
        String color = editTextColorAnimal.getText().toString();
        if (color.length() == 0) {
            editTextColorAnimal.setError("Укажите цвет");
            return false;
        }

        return true;
    }

    private boolean IsCorrectWeight() {
        String textWeight = editTextWeightAnimal.getText().toString();
        try {
            Double.parseDouble(textWeight);
        } catch (NumberFormatException e) {
            editTextWeightAnimal.setError("Некорректный вес");
            return false;
        }

        return true;
    }

    private boolean IsCorrectAge() {
        String textAge = editTextAgeAnimal.getText().toString();
        try {
            Integer.parseInt(textAge);
        } catch (NumberFormatException e) {
            editTextAgeAnimal.setError("Некорректный возраст");
            return false;
        }

        return true;
    }

    private boolean IsCorrectName() {
        String name = editTextNameAnimal.getText().toString();
        if (name.length() == 0) {
            editTextNameAnimal.setError("Укажите название");
            return false;
        }

        return true;
    }
}