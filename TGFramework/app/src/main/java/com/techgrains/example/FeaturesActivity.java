package com.techgrains.example;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.techgrains.model.dialog.TGProgressDialogModel;
import com.techgrains.ui.TGActivity;


public class FeaturesActivity extends TGActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);

        setProgressDialog();
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
