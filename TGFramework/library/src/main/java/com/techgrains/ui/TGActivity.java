package com.techgrains.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;

import com.techgrains.dialog.TGIAlertDialog;
import com.techgrains.dialog.TGIProgressDialog;
import com.techgrains.model.dialog.TGDialogModel;
import com.techgrains.model.dialog.TGProgressDialogModel;
import com.techgrains.util.TGUtil;

public class TGActivity extends FragmentActivity implements TGIAlertDialog, TGIProgressDialog {

    private ProgressDialog mProgressDialog = null;

    @Override
    public void showAlertDialog(TGDialogModel dialogModel) {
        if (dialogModel == null) {
            return;
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        if (TGUtil.hasValue(dialogModel.getTitle())) {
            dialog.setTitle(dialogModel.getTitle());
        }
        if (TGUtil.hasValue(dialogModel.getMessage())) {
            dialog.setMessage(dialogModel.getMessage());
        }

        if (TGUtil.hasValue(dialogModel.getPositiveText())) {
            dialog.setPositiveButton(dialogModel.getPositiveText(), dialogModel.getOnPositiveCLick());

        }

        if (TGUtil.hasValue(dialogModel.getNegativeText())) {
            dialog.setPositiveButton(dialogModel.getNegativeText(), dialogModel.getOnNegativeClick());

        }

        if (TGUtil.hasValue(dialogModel.getNeutralText())) {
            dialog.setNeutralButton(dialogModel.getNeutralText(), dialogModel.getOnNeutralClick());
        }

        dialog.setCancelable(dialogModel.isCancellable());


        if (TGUtil.hasValue(dialogModel.getIcon() != -1)) {
            dialog.setIcon(dialogModel.getIcon());
        }
        dialog.show();
    }

    @Override
    public void showProgressDialog(TGProgressDialogModel dialogModel) {
        dismissProgressDialog();
        try {
            mProgressDialog = ProgressDialog.show(this, dialogModel.getTitle(), dialogModel.getMessage(), dialogModel.isCancelable());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isShowingProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing())
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void dismissProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing())
                mProgressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
