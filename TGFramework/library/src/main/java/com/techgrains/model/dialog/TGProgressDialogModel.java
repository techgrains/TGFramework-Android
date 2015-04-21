package com.techgrains.model.dialog;

/**
 * Created by CNS on 21/04/15.
 */

/**
 * Created by CNS on 21/04/15.
 */
public class TGProgressDialogModel {

    private String title;
    private String message = null;
    private boolean cancelable;

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