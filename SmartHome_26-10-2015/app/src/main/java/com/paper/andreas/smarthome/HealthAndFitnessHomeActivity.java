package com.paper.andreas.smarthome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class HealthAndFitnessHomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText mNameEditText;
    private EditText mHeightEditText;
    private EditText mWeightEditText;
    private Spinner mGenderSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_and_fitness_home);

        mNameEditText = (EditText) findViewById(R.id.personnameText);
        mHeightEditText = (EditText) findViewById(R.id.heightText);
        mWeightEditText = (EditText) findViewById(R.id.weightText);
        mGenderSpinner = (Spinner)findViewById(R.id.genderspinner);


        mNameEditText.setText(getPersonName(this));
        mHeightEditText.setText(getPersonHeight(this));
        mWeightEditText.setText(getPersonWeight(this));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(adapter);
        mGenderSpinner.setOnItemSelectedListener(this);
        mGenderSpinner.setSelection(getPersonGender(this));



        mNameEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                // you can call or do what you want with your EditText here
                String name = s.toString();
                savePersonName(name, getBaseContext());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


        mHeightEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                // you can call or do what you want with your EditText here
                String height = s.toString();
                savePersonHeight(height, getBaseContext());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


        mWeightEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                // you can call or do what you want with your EditText here
                String weight = s.toString();
                savePersonWeight(weight, getBaseContext());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

    }




    private static void savePersonName(String name, Context context) {
        if (context == null){
            return;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.sharedPreferencesFilename), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("personname", name);
        editor.commit();
    }

    public static String getPersonName(Context context) {
        if (context == null){
            return "";
        }
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.sharedPreferencesFilename), Context.MODE_PRIVATE);
        String name = sharedPref.getString("personname", "");
        return name;
    }





    private static void savePersonHeight(String height, Context context) {
        if (context == null){
            return;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.sharedPreferencesFilename), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("personheight", height);
        editor.commit();
    }

    public static String getPersonHeight(Context context) {
        if (context == null){
            return "";
        }
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.sharedPreferencesFilename), Context.MODE_PRIVATE);
        String height = sharedPref.getString("personheight", "");
        return height;
    }






    private static void savePersonWeight(String weight, Context context) {
        if (context == null){
            return;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.sharedPreferencesFilename), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("personweight", weight);
        editor.commit();
    }

    public static String getPersonWeight(Context context) {
        if (context == null){
            return "";
        }
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.sharedPreferencesFilename), Context.MODE_PRIVATE);
        String weight = sharedPref.getString("personweight", "");
        return weight;
    }





    private static void savePersonGender(int gender, Context context) {
        if (context == null){
            return;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.sharedPreferencesFilename), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("persongender", gender);
        editor.commit();
    }

    public static int getPersonGender(Context context) {
        if (context == null){
            return 0;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.sharedPreferencesFilename), Context.MODE_PRIVATE);
        int gender = sharedPref.getInt("persongender", 0);
        return gender;
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        savePersonGender(position, this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}



    public void onHealthAndFitnessDetails(View v) {
        Intent startIntent = new Intent(this, HealthAndFitnessDetailsActivity.class);
        startActivity(startIntent);
    }


}
