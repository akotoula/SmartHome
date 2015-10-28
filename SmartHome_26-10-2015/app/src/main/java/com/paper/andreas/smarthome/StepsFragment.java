package com.paper.andreas.smarthome;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Adreas on 27/9/2015.
 */
public class StepsFragment extends android.support.v4.app.Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private EditText mStepsGoalEditText;






    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static StepsFragment newInstance(int sectionNumber) {
        StepsFragment fragment = new StepsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public StepsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        mStepsGoalEditText = (EditText) rootView.findViewById(R.id.stepseditText);
        mStepsGoalEditText.setText(Integer.toString(getStepsGoal(getActivity())));

        mStepsGoalEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                // you can call or do what you want with your EditText here
                String steps = s.toString();
                try {
                    saveStepsGoal(Integer.parseInt(steps), getActivity());
                }
                catch (Exception e) {}

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        return rootView;
    }



    private static void saveStepsGoal(int stepsGoal, Context context) {
        if (context == null){
            return;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.sharedPreferencesFilename), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("stepsgoal", stepsGoal);
        editor.commit();
    }

    public static int getStepsGoal(Context context) {
        if (context == null){
            return 0;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.sharedPreferencesFilename), Context.MODE_PRIVATE);
        int steps = sharedPref.getInt("stepsgoal", 0);
        return steps;
    }






}
