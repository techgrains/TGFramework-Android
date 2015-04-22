package com.techgrains.model.dialog;

import android.content.DialogInterface;

/**
 * Created by CNS on 21/04/15.
 */
public class TGAlertDialog {

    private String title;
    private String message;

    private String positiveButtonText = "OK";
    private String negativeButtonText;
    private String neutralButtonText;

    private DialogInterface.OnClickListener onPositiveCLick;
    private DialogInterface.OnClickListener onNegativeClick;
    private DialogInterface.OnClickListener onNeutralClick;

    private boolean cancellable;
    private int icon = -1;

    public TGAlertDialog(String title, String message, String positiveButtonText) {
        this.title = title;
        this.message = message;
        this.positiveButtonText = positiveButtonText;
    }

    public String getNeutralButtonText() {
        return neutralButtonText;
    }

    public void setNeutralButtonText(String neutralButtonText) {
        this.neutralButtonText = neutralButtonText;
    }

    public DialogInterface.OnClickListener getOnNeutralClick() {
        return onNeutralClick;
    }

    public void setOnNeutralClick(DialogInterface.OnClickListener onNeutralClick) {
        this.onNeutralClick = onNeutralClick;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPositiveButtonText() {
        return positiveButtonText;
    }

    public void setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
    }

    public DialogInterface.OnClickListener getOnPositiveCLick() {
        return onPositiveCLick;
    }

    public void setOnPositiveCLick(DialogInterface.OnClickListener onPositiveCLick) {
        this.onPositiveCLick = onPositiveCLick;
    }

    public String getNegativeButtonText() {
        return negativeButtonText;
    }

    public void setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
    }

    public DialogInterface.OnClickListener getOnNegativeClick() {
        return onNegativeClick;
    }

    public void setOnNegativeClick(DialogInterface.OnClickListener onNegativeClick) {
        this.onNegativeClick = onNegativeClick;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    public void setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


}