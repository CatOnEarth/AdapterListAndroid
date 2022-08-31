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

    /** EditText to input animal's name */
    private EditText editTextNameAnimal;
    /** EditText to input animal's age */
    private EditText editTextAgeAnimal;
    /** EditText to input animal's length */
    private EditText editTextLengthAnimal;
    /** EditText to input animal's weight */
    private EditText editTextWeightAnimal;
    /** EditText to input animal's color */
    private EditText editTextColorAnimal;

    /** onCreate method of MainActivity
     *
     * @param savedInstanceState a reference to a Bundle object that is passed into the onCreate method of every Android Activity
     */
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

    /**Method to check correction and save info about animal
     *
     */
    private void SaveAnimalInfo() {
        if (IsCorrectInfo()) {
            SaveAnimal();
        }
    }

    /**Method to save animal in SQLITE
     *
     */
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

    /**Method to check correction input data about animal
     *
     * @return true if all i correct
     * @return false if find incorrect input
     */
    private boolean IsCorrectInfo() {
        return IsCorrectName() && IsCorrectColor() && IsCorrectWeight()
                     && IsCorrectLength() && IsCorrectAge();
    }

    /**Method to check correction input length of animal
     *
     * @return true if it's a number and > 0
     * @return false if it's not a number <= 0
     */
    private boolean IsCorrectLength() {
        String textLength = editTextLengthAnimal.getText().toString();
        try {
            double length = Double.parseDouble(textLength);
            if (length <= 0) {
                editTextLengthAnimal.setError("Укажите длину > 0");
                return false;
            }
        } catch (NumberFormatException e) {
            editTextLengthAnimal.setError("Укажите длину");
            return false;
        }

        return true;
    }

    /**Method to check correction input color of animal
     *
     * @return true if it's not empty
     * @return false if it's empty
     */
    private boolean IsCorrectColor() {
        String color = editTextColorAnimal.getText().toString();
        if (color.length() == 0) {
            editTextColorAnimal.setError("Укажите цвет");
            return false;
        }

        return true;
    }

    /**Method to check correction input weight of animal
     *
     * @return true if it's a number and > 0
     * @return false if it's not a number or =< 0
     */
    private boolean IsCorrectWeight() {
        String textWeight = editTextWeightAnimal.getText().toString();
        try {
            double weight = Double.parseDouble(textWeight);
            if (weight <= 0) {
                editTextWeightAnimal.setError("Укажите вес > 0");
                return false;
            }
        } catch (NumberFormatException e) {
            editTextWeightAnimal.setError("Некорректный вес");
            return false;
        }

        return true;
    }

    /**Method to check correction input age of animal
     *
     * @return true if it's a number and > 0
     * @return false if it's not a number or < 0
     */
    private boolean IsCorrectAge() {
        String textAge = editTextAgeAnimal.getText().toString();
        try {
            int age = Integer.parseInt(textAge);
            if (age <= 0) {
                editTextAgeAnimal.setError("Укажите возраст > 0");
                return false;
            }
        } catch (NumberFormatException e) {
            editTextAgeAnimal.setError("Некорректный возраст");
            return false;
        }

        return true;
    }

    /**Method to check correction input name of animal
     *
     * @return true if it's not empty
     * @return false if it's empty
     */
    private boolean IsCorrectName() {
        String name = editTextNameAnimal.getText().toString();
        if (name.length() == 0) {
            editTextNameAnimal.setError("Укажите название");
            return false;
        }

        return true;
    }
}