package com.paper.andreas.smarthome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class SmartPlugAdapter extends ArrayAdapter<SmartPlug> {

    private final Context mContext;
    private final ArrayList<SmartPlug> mSmartPlugs;

    public SmartPlugAdapter(Context context, ArrayList<SmartPlug> smartplugs) {
        super(context, R.layout.list_row, smartplugs);
        mContext = context;
        mSmartPlugs = smartplugs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_row, parent,false);
        ImageView imageIcon = (ImageView) rowView.findViewById(R.id.imageIcon);
        TextView textAppName = (TextView) rowView.findViewById(R.id.textAppName);
        TextView textAppDescr = (TextView) rowView.findViewById(R.id.textViewAppDescr);

        SmartPlug plug = getItem(position);
        rowView.setTag(plug);

        textAppName.setText(plug.getmId());
        textAppDescr.setText(plug.getmApplianceConnected() + " - " + plug.getmLocation());

        return rowView;
    }

}
