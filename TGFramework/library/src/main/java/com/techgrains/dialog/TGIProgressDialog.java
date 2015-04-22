package com.techgrains.dialog;

import com.techgrains.model.dialog.TGProgressDialog;

/**
 * Created by Vinay on 21/04/15.
 */
public interface TGIProgressDialog {

    /**
     * Shows progress dialog, based on the properties of passed parameter object
     *
     * @param progressDialog TGProgressDialog
     */
    public void showProgressDialog(TGProgressDialog progressDialog);

    /**
     * Checks: is progress dialog still showing
     */
    public boolean isShowingProgressDialog();

    /**
     * Dismisses progress dialog
     */
    public void dismissProgressDialog();
}
