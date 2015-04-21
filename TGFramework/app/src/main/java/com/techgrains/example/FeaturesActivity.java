package com.techgrains.example;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.techgrains.example.fragment.ActivityDemoFragment;
import com.techgrains.model.dialog.TGDialogModel;
import com.techgrains.model.dialog.TGProgressDialogModel;
import com.techgrains.ui.TGActivity;


public class FeaturesActivity extends TGActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);

        setProgressDialog();

        setAlertDialog();

        setDemoFragment();
    }

    private void setDemoFragment() {
        findViewById(R.id.features_btn_Fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFragment(ActivityDemoFragment.newInstance(), true, R.id.features_frag_container);

            }
        });
    }

    private void setAlertDialog() {
        findViewById(R.id.features_btn_ShowAlertDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = "Title";
                String message = "message";
                String positiveText = "positive";
                DialogInterface.OnClickListener onPositiveCLick = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                String negativeText = "negative";
                DialogInterface.OnClickListener onNegativeClick = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                boolean cancellable = false;

                TGDialogModel dModel = new TGDialogModel(title, message, positiveText, onPositiveCLick, negativeText, onNegativeClick, cancellable);

                showAlertDialog(dModel);
            }
        });
    }

    private void setProgressDialog() {
        findViewById(R.id.features_btn_ShowProgressDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TGProgressDialogModel pModel = new TGProgressDialogModel("Title", "Message", false, R.drawable.spinner);
                showProgressDialog(pModel);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressDialog();
                    }
                }, 5000);
            }
        });
    }


    protected void showFragment(android.support.v4.app.Fragment fragment, boolean addToBackStack, int idFragmentContainer) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(idFragmentContainer, fragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        // Commit the transaction
        fragmentTransaction.commit();
    }

}
