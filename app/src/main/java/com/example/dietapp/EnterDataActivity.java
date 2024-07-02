package com.example.dietapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EnterDataActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILE_NAME = "dietapp\n" + "dietapp\n" + "dietapp";
    private static final String KEY_MY_WEIGHT = "myWeight";
    private static final String KEY_MY_HEIGHT = "myHeight";
    private static final String KEY_MY_NAME = "myName";
    private static final String KEY_MY_AGE = "myAge";

    private EditText enterName;
    private EditText enterWeight;
    private EditText enterHeight;
    private EditText enterAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // finding views
        enterHeight = findViewById(R.id.enterHeight);
        enterWeight = findViewById(R.id.enterWeight);
        enterName = findViewById(R.id.enterName);
        enterAge = findViewById(R.id.enterAge);
        Spinner activityDropDown = findViewById(R.id.activityDropDown);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.activity_drop_down_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activityDropDown.setAdapter(adapter);
    }

    public void setWeightToPreference(int weight){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //store the weight integer
        editor.putInt(KEY_MY_WEIGHT, weight);
        editor.apply();
    }
    public void setHeightToPreference(int height){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // store the height integer
        editor.putInt(KEY_MY_HEIGHT, height);
        editor.apply();
    }
    public void setNameToPreference(String name){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // store the name String
        editor.putString(KEY_MY_NAME, name);
        editor.apply();
    }
    public void setAgeToPreference(int age){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // store the height integer
        editor.putInt(KEY_MY_AGE, age);
        editor.apply();
    }

    // when the button is pressed it will check if all the fields are filled in
    public void onButtonClicked(View caller){
        if (checkIfAllFilled()) {
            // set all preference variables
            setWeightToPreference(Integer.parseInt(enterWeight.getText().toString()));
            setHeightToPreference(Integer.parseInt(enterHeight.getText().toString()));
            setNameToPreference(enterName.getText().toString());
            setAgeToPreference(Integer.parseInt(enterAge.getText().toString()));

            // go back to the opening activity
            Intent intent = new Intent(this, OpeningActivity.class);
            startActivity(intent);
        } else {
            // if one or more of the EditTexts is not filled a toast message displays
            Toast.makeText(EnterDataActivity.this, "Please enter all of the fields", Toast.LENGTH_LONG).show();
        }
    }

    // checks if all of the EditTexts are filled
    public boolean checkIfAllFilled(){
        // returns a true if all fields are filled in
        return !enterName.getText().toString().isEmpty() && !enterWeight.getText().toString().isEmpty() && !enterHeight.getText().toString().isEmpty() && !enterAge.getText().toString().isEmpty();
    }
}