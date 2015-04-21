package com.techgrains.example;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

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


}
