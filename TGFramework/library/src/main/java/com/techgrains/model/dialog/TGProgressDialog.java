package com.techgrains.model.dialog;

import android.support.annotation.DrawableRes;

/**
 * Created by CNS on 21/04/15.
 */
public class TGProgressDialog {

    private String title;
    private String message;
    private boolean cancelable = true;
    private int indeterminateDrawable = -1;

    /**
     * Progress dialog with title and message
     *
     * @param title
     * @param message
     */

    public TGProgressDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }

    /**
     * Progress dialog with title, message and indeterminateDrawable
     *
     * @param title
     * @param message
     * @param indeterminateDrawable
     */
    public TGProgressDialog(String title, String message, @DrawableRes int indeterminateDrawable) {
        this.title = title;
        this.message = message;
        this.indeterminateDrawable = indeterminateDrawable;
    }

    /**
     * @return Drawable resource id for indeterminateDrawable
     */
    public int getIndeterminateDrawable() {
        return indeterminateDrawable;
    }

    /**
     * Sets Drawable resource id for indeterminateDrawable
     *
     * @param indeterminateDrawable
     */
    public void setIndeterminateDrawable(@DrawableRes int indeterminateDrawable) {
        this.indeterminateDrawable = indeterminateDrawable;
    }

    /**
     * Check dialog cancelable state
     *
     * @return boolean
     */
    public boolean isCancelable() {
        return cancelable;
    }

    /**
     * Sets dialog cancellable state
     *
     * @param cancelable boolean
     */
    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    /**
     * Returns title of alert dialog
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title of alert dialog
     *
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns message of alert dialog
     *
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message of alert dialog
     *
     * @param message String
     */
    public void setMessage(String message) {
        this.message = message;
    }

}