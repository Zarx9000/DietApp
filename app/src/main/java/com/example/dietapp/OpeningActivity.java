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

public class OpeningActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILE_NAME = "dietapp\n" + "dietapp\n" + "dietapp";
    private static final String KEY_MY_WEIGHT = "myWeight";
    private static final String KEY_MY_HEIGHT = "myHeight";
    private static final String KEY_MY_NAME = "myName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // checks for if the preference variables have been instantiated or not
        System.out.println("heck ye");
        if (getHeightFromPreference() == -1 || getWeightFromPreference() == -1 || getNameFromPreference().equals("empty")){
            // if true goes to new activity to enter preference variables
            Intent intent = new Intent(this, EnterDataActivity.class);
            startActivity(intent);
        }
    }

    public int getWeightFromPreference(){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        // get the weight variable from the preference (is -1 on null, so when not instantiated yet)
        return sharedPreferences.getInt(KEY_MY_WEIGHT, -1);
    }
    public int getHeightFromPreference(){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        // get the weight variable from the preference (is -1 on null, so when not instantiated yet)
        return sharedPreferences.getInt(KEY_MY_HEIGHT, -1);
    }
    public String getNameFromPreference(){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        // get the weight variable from the preference (is "empty" on null, so when not instantiated yet)
        return sharedPreferences.getString(KEY_MY_NAME, "empty");
    }
}