package com.paper.andreas.smarthome;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;


public class SmartPlugsListActivity extends ActionBarActivity implements NewSmartPlugDialogFragment.NewSmartPlugDialogFragmentListener {

    private static ArrayList<SmartPlug> mSmartPlugs;
    private Context mContext;
    private ListView mListview;
    private SmartPlugAdapter mAdapter;
    private EditText mKwhPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_plugs_list);
        mContext = this;

        if (mSmartPlugs == null) {
            mSmartPlugs = new ArrayList<SmartPlug>();
            mSmartPlugs.add(new SmartPlug("SmartPlug_01", "Air Condition", "Master Bedroom", true));
            mSmartPlugs.add(new SmartPlug("SmartPlug_02", "Refridgerator", "Kitchen", true));
        }


        mListview = (ListView) findViewById(R.id.listview);
        mAdapter = new SmartPlugAdapter(this, mSmartPlugs);
        mListview.setAdapter(mAdapter);
 /*
                String packageName = app.getmPackageName();
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
                if (launchIntent == null) { //not installed
                    String apkurl = app.getmApkUrl();
                    String apkTitle = app.getmTitle();
                    String apkDescr = app.getmShortDescription();
                    String apkIconName = app.getmImageName();
                    ConfirmInstallActivity.show(apkurl, Utilities.createApkDownloadPath(packageName, AppContextProvider.getContext()), apkTitle, apkDescr, apkIconName);
                } else { //launch app
                    AppContextProvider.getContext().startActivity(launchIntent);
                }
                */
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SmartPlug plug = (SmartPlug) view.getTag();

                Intent startIntent = new Intent(mContext, SmartPlugDetailsActivity.class);
                startIntent.putExtra("plug_id", plug.getmId());
                startIntent.putExtra("plug_appliance", plug.getmApplianceConnected());
                startIntent.putExtra("plug_location", plug.getmLocation());
                startIntent.putExtra("plug_connection", plug.ismConnection());
                mContext.startActivity(startIntent);
            }

        });


        mKwhPrice = (EditText) findViewById(R.id.price);
        mKwhPrice.setText(Float.toString(getKwhPrice(this)));

        mKwhPrice.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                // you can call or do what you want with your EditText here
                String priceStr = s.toString();
                try {
                    float price = Float.valueOf(priceStr);
                    saveKwhPrice( price, getBaseContext());
                }
                catch (Exception e) {}

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });



}

    @Override
    public void addSmartPlug(SmartPlug newSmartPlug) {
        mSmartPlugs.add(newSmartPlug);
        mAdapter.notifyDataSetChanged();

    }

    public void onNewSmartPlug(View v) {
        DialogFragment newFragment = new NewSmartPlugDialogFragment();
        newFragment.show(getFragmentManager(), "Add smartplug");
    }

    public void onScanNewSmartPlug(View v) {
        //DialogFragment newFragment = new NewSmartPlugDialogFragment();
        //newFragment.show(getFragmentManager(), "Add smartplug");

        IntentIntegrator integrator = new IntentIntegrator(SmartPlugsListActivity.this);
        integrator.initiateScan();
    }


    private static void saveKwhPrice(float price, Context context) {
        if (context == null){
            return;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.sharedPreferencesFilename), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("kwhprice", price);
        editor.commit();
    }


    public static float getKwhPrice(Context context) {
        if (context == null){
            return 0;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.sharedPreferencesFilename), Context.MODE_PRIVATE);
        float price = sharedPref.getFloat("kwhprice", 0);
        return price;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String re = scanResult.getContents();

            DialogFragment newFragment = new NewSmartPlugDialogFragment();

            Bundle args = new Bundle();
            args.putString("smartplugname", re);
            newFragment.setArguments(args);

            newFragment.show(getFragmentManager(), "Add smartplug");

        }

    }


}
