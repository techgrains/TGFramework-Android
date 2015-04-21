package com.techgrains.model.dialog;

import android.support.annotation.DrawableRes;

/**
 * Created by CNS on 21/04/15.
 */
public class TGProgressDialogModel {

    private String title;
    private String message = null;
    private boolean cancelable;
    private int indeterminateDrawable = -1;

    public TGProgressDialogModel(String title, String message, boolean cancelable) {
        this.title = title;
        this.message = message;
        this.cancelable = cancelable;
    }

    public TGProgressDialogModel(String title, String message, boolean cancelable, @DrawableRes int indeterminateDrawable) {

        this.title = title;
        this.message = message;
        this.cancelable = cancelable;
        this.indeterminateDrawable = indeterminateDrawable;
    }

    public int getIndeterminateDrawable() {
        return indeterminateDrawable;
    }

    public void setIndeterminateDrawable(@DrawableRes int indeterminateDrawable) {
        this.indeterminateDrawable = indeterminateDrawable;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}