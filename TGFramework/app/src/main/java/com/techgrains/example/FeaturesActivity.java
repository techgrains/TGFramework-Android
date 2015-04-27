package com.techgrains.example;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.techgrains.example.fragment.ActivityDemoFragment;
import com.techgrains.model.dialog.TGAlertDialog;
import com.techgrains.model.dialog.TGProgressDialog;
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

                TGAlertDialog dModel = new TGAlertDialog(title, message, positiveText);
                dModel.setOnPositiveCLick(onPositiveCLick);
                dModel.setNegativeButtonText(negativeText);
                dModel.setOnNegativeClick(onNegativeClick);
                dModel.setCancellable(cancellable);

                showAlertDialog(dModel);
            }
        });
    }

    private void setProgressDialog() {
        findViewById(R.id.features_btn_ShowProgressDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TGProgressDialog pModel = new TGProgressDialog("Title", "Message", R.drawable.spinner);
                pModel.setCancelable(false);

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


    protected void showFragment(Fragment fragment, boolean addToBackStack, int idFragmentContainer) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(idFragmentContainer, fragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        // Commit the transaction
        fragmentTransaction.commit();
    }

}
