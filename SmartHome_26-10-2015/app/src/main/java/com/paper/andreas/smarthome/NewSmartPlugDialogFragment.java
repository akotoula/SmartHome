package com.paper.andreas.smarthome;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;




/**
 * Created by pstamp on 10/9/2015.
 */
public class NewSmartPlugDialogFragment extends DialogFragment {

    //private Incident mCurrentIncident;

    private TextView mTitleTextView;
    private EditText mNameEditText;
    private EditText mApplianceEditText;
    private EditText mLocaionEditText;

    public interface NewSmartPlugDialogFragmentListener {
        public void addSmartPlug(SmartPlug smartPlug);

    }

    NewSmartPlugDialogFragmentListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NewSmartPlugDialogFragmentListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //mCurrentIncident = (Incident) getArguments().getSerializable("incident");


        View dialogView = inflater.inflate(R.layout.dialog_newsmartplug, null);
        mNameEditText = (EditText) dialogView.findViewById(R.id.nameText);
        Bundle args =  getArguments();
        if (args != null) {
            String name = getArguments().getString("smartplugname");
            if (name != null && !name.equalsIgnoreCase("")) {
                mNameEditText.setText(name);
            }
        }


        mApplianceEditText = (EditText) dialogView.findViewById(R.id.applianceText);
        mLocaionEditText = (EditText) dialogView.findViewById(R.id.locationText);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.addSmartPlug(new SmartPlug(mNameEditText.getText().toString(), mApplianceEditText.getText().toString(), mLocaionEditText.getText().toString(), true));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NewSmartPlugDialogFragment.this.getDialog().cancel();
                    }
                });



        return builder.create();
    }


}
