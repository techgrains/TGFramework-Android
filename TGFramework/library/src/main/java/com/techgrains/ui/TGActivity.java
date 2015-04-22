package com.techgrains.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;

import com.techgrains.dialog.TGIAlertDialog;
import com.techgrains.dialog.TGIProgressDialog;
import com.techgrains.model.dialog.TGAlertDialog;
import com.techgrains.model.dialog.TGProgressDialog;
import com.techgrains.util.TGUtil;

public abstract class TGActivity extends FragmentActivity implements TGIAlertDialog, TGIProgressDialog {

    private ProgressDialog mProgressDialog = null;

    /**
     * Shows alert dialog, based on the properties of passed parameter object
     *
     * @param alertDialog TGAlertDialog
     */
    @Override
    public void showAlertDialog(TGAlertDialog alertDialog) {
        if (alertDialog == null) {
            return;
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        if (TGUtil.hasValue(alertDialog.getTitle())) {
            dialog.setTitle(alertDialog.getTitle());
        }

        if (TGUtil.hasValue(alertDialog.getMessage())) {
            dialog.setMessage(alertDialog.getMessage());
        }

        if (TGUtil.hasValue(alertDialog.getPositiveButtonText())) {
            dialog.setPositiveButton(alertDialog.getPositiveButtonText(), alertDialog.getOnPositiveCLick());
        }

        if (TGUtil.hasValue(alertDialog.getNegativeButtonText())) {
            dialog.setPositiveButton(alertDialog.getNegativeButtonText(), alertDialog.getOnNegativeClick());
        }

        if (TGUtil.hasValue(alertDialog.getNeutralButtonText())) {
            dialog.setNeutralButton(alertDialog.getNeutralButtonText(), alertDialog.getOnNeutralClick());
        }

        dialog.setCancelable(alertDialog.isCancellable());

        if (TGUtil.hasValue(alertDialog.getIcon() != -1)) {
            dialog.setIcon(alertDialog.getIcon());
        }
        dialog.show();
    }

    /**
     * Shows progress dialog, based on the properties of passed parameter object
     *
     * @param progressDialog TGProgressDialog
     */
    @Override
    public void showProgressDialog(TGProgressDialog progressDialog) {
        dismissProgressDialog();
        if (progressDialog == null) {
            return;
        }
        mProgressDialog = ProgressDialog.show(this, progressDialog.getTitle(), progressDialog.getMessage(), progressDialog.isCancelable());
        if (progressDialog.getIndeterminateDrawable() != -1) {
            mProgressDialog.setIndeterminateDrawable(getResources().getDrawable(progressDialog.getIndeterminateDrawable()));
        }
    }

    /**
     * Checks: is progress dialog still showing
     */
    @Override
    public boolean isShowingProgressDialog() {
        return mProgressDialog != null && mProgressDialog.isShowing();
    }

    /**
     * Dismisses progress dialog
     */
    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
}
