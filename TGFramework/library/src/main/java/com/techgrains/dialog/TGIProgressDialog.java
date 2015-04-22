package com.techgrains.dialog;

import com.techgrains.model.dialog.TGProgressDialog;

/**
 * Created by CNS on 21/04/15.
 */
public interface TGIProgressDialog {

    public void showProgressDialog(TGProgressDialog dialogModel);

    public boolean isShowingProgressDialog();

    public void dismissProgressDialog();
}
