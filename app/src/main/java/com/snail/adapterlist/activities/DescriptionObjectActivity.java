package com.snail.adapterlist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.snail.adapterlist.R;
import com.snail.adapterlist.db.DBHelper;

/** DescriptionObjectActivity activity
 *
 */
public class DescriptionObjectActivity extends AppCompatActivity {

    /** KEY to save/get id(sqlite) from intent */
    public static final String KEY_ID     = "ID";
    /** KEY to save/get name from intent */
    public static final String KEY_NAME   = "NAME";
    /** KEY to save/get age animal from intent */
    public static final String KEY_AGE    = "AGE";
    /** KEY to save/get length animal from intent */
    public static final String KEY_LENGTH = "LENGTH";
    /** KEY to save/get weight animal from intent */
    public static final String KEY_WEIGHT = "WEIGHT";
    /** KEY to save/get color animal from intent */
    public static final String KEY_COLOR  = "COLOR";

    /** textView to display name animal */
    private TextView textName;
    /** textView to display age animal */
    private TextView textAge;
    /** textView to display length animal */
    private TextView textLength;
    /** textView to display weight animal */
    private TextView textWeight;
    /** textView to display color animal */
    private TextView textColor;


    /** onCreate method of MainActivity
     *
     * @param savedInstanceState a reference to a Bundle object that is passed into the onCreate method of every Android Activity
     */
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

    /** Method to set data in textviews
     *
     */
    private void setDataDescription() {
        Intent intent = getIntent();

        textName.setText(intent.getStringExtra(KEY_NAME));
        textAge.setText(String.valueOf(intent.getIntExtra(KEY_AGE, -1)));
        textLength.setText(String.valueOf(intent.getDoubleExtra(KEY_LENGTH, -1.0)));
        textWeight.setText(String.valueOf(intent.getDoubleExtra(KEY_WEIGHT, -1.0)));
        textColor.setText(intent.getStringExtra(KEY_COLOR));
    }

    /**Method to delete animal from SQLITE
     *
     */
    private void DeleteAnimal() {
        DBHelper db = new DBHelper(this);
        db.deleteAnimal(getIntent().getLongExtra(KEY_ID, -1));
        finish();
    }
}