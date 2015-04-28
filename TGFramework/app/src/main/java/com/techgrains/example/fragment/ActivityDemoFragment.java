package com.techgrains.example.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techgrains.dialog.TGIAlertDialog;
import com.techgrains.dialog.TGIProgressDialog;
import com.techgrains.example.R;
import com.techgrains.model.dialog.TGAlertDialog;
import com.techgrains.model.dialog.TGProgressDialog;

public class ActivityDemoFragment extends Fragment {

    private TGIProgressDialog mProgressDialogListener;
    private TGIAlertDialog mAlertDialogListener;

    public ActivityDemoFragment() {
        // Required empty public constructor
    }

    public static ActivityDemoFragment newInstance() {
        ActivityDemoFragment fragment = new ActivityDemoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tgactivity_demo, container, false);
        setProgressDialog(root);

        setAlertDialog(root);
        return root;
    }


    private void setAlertDialog(View root) {
        root.findViewById(R.id.featuresFrag_btn_ShowAlertDialog).setOnClickListener(new View.OnClickListener() {
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


                mAlertDialogListener.showAlertDialog(dModel);
            }
        });
    }

    private void setProgressDialog(View root) {
        root.findViewById(R.id.featuresFrag_btn_ShowProgressDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TGProgressDialog pModel = new TGProgressDialog("Title", "Message", R.drawable.spinner);
                pModel.setCancelable(false);

                mProgressDialogListener.showProgressDialog(pModel);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressDialogListener.dismissProgressDialog();
                    }
                }, 5000);
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof TGIProgressDialog) {
            mProgressDialogListener = (TGIProgressDialog) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement TGIProgressDialog");
        }

        if (activity instanceof TGIAlertDialog) {
            mAlertDialogListener = (TGIAlertDialog) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement TGIAlertDialog");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mProgressDialogListener = null;
        mAlertDialogListener = null;
    }


}
