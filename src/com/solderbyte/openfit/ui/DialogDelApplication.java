package com.solderbyte.openfit.ui;

import com.solderbyte.openfit.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.ListAdapter;

public class DialogDelApplication extends DialogFragment {
    private static final String LOG_TAG = "OpenFit:DialogDelApplication";
    private static final String INTENT_UI_DELAPPLICATION = "com.solderbyte.openfit.ui.delapplication";

    private CharSequence[] packageNames = new CharSequence[0];
    private CharSequence[] appNames = new CharSequence[0];
    private ListAdapter addApplication;

    public DialogDelApplication(ListAdapter adapter, CharSequence[] pNames, CharSequence[] aNames) {
        addApplication = adapter;
        packageNames = pNames;
        appNames = aNames;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title_del);
        builder.setAdapter(addApplication, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int index) {
                Intent msg = new Intent(INTENT_UI_DELAPPLICATION);
                msg.putExtra("packageName", packageNames[index]);
                msg.putExtra("appName", appNames[index]);
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(msg);
                Log.d(LOG_TAG, "Clicked: " + appNames[index] + " : " + packageNames[index]);
            }
        });
        builder.setPositiveButton(R.string.dialog_close_del,  new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int index) {}
        });

        return builder.create();
    }

}
