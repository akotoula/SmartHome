package com.paper.andreas.smarthome;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

/**
 * Created by Andreas on 27/9/2015.
 */
public class SleepFragment extends android.support.v4.app.Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private DatePicker mDatePicker;

    private Button mSleepDataButton;

    private OnSleepFragmentInteractionListener mListener;

    public interface OnSleepFragmentInteractionListener {
        public void onSleepDataSelected(String date);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnSleepFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnQuestionFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SleepFragment newInstance(int sectionNumber) {
        SleepFragment fragment = new SleepFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public SleepFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sleep, container, false);

        mDatePicker = (DatePicker) rootView.findViewById(R.id.datePicker);

        mSleepDataButton = (Button)  rootView.findViewById(R.id.sleepDataButton);
        mSleepDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = mDatePicker.getDayOfMonth();
                int month = mDatePicker.getMonth() + 1;
                int year = mDatePicker.getYear();

                String yearStr = Integer.toString(year);
                String monthStr = Integer.toString(month);
                if(monthStr.length() == 1) {
                    monthStr = "0" + monthStr;
                }
                String dayStr = Integer.toString(day);
                if(dayStr.length() == 1) {
                    dayStr = "0" + dayStr;
                }

                String date = yearStr + "-" + monthStr + "-" + dayStr;


                Log.i("","");

                mListener.onSleepDataSelected(date);
            }
        });


        return rootView;
    }
}
