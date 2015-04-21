package com.techgrains.dialog;

import com.techgrains.model.dialog.TGProgressDialogModel;

/**
 * Created by CNS on 21/04/15.
 */
public interface TGIProgressDialog {

    public void showProgressDialog(TGProgressDialogModel dialogModel);

    public boolean isShowingProgressDialog();

    public void dismissProgressDialog();
}
