package com.techgrains.model.dialog;

import android.content.DialogInterface;

/**
 * Created by CNS on 21/04/15.
 */
public class TGDialogModel {

    private String title;
    private String message = null;

    private String positiveText = null;
    private DialogInterface.OnClickListener onPositiveCLick = null;

    private String negativeText = null;
    private DialogInterface.OnClickListener onNegativeClick = null;

    private String NeutralText = null;
    private DialogInterface.OnClickListener onNeutralClick = null;

    private boolean cancellable;
    private int icon = -1;

    public TGDialogModel(String title, String message, String positiveText, DialogInterface.OnClickListener onPositiveCLick, String negativeText, DialogInterface.OnClickListener onNegativeClick, boolean cancellable) {
        this.title = title;
        this.message = message;
        this.positiveText = positiveText;
        this.onPositiveCLick = onPositiveCLick;
        this.negativeText = negativeText;
        this.onNegativeClick = onNegativeClick;
        this.cancellable = cancellable;
    }

    public TGDialogModel(String title, String message, String positiveText, DialogInterface.OnClickListener onPositiveCLick, boolean cancellable) {
        this.title = title;
        this.message = message;
        this.positiveText = positiveText;
        this.onPositiveCLick = onPositiveCLick;
        this.cancellable = cancellable;
    }

    public TGDialogModel(String title, String message, String positiveText, boolean cancellable) {
        this.title = title;
        this.message = message;
        this.positiveText = positiveText;
        this.cancellable = cancellable;
    }

    public TGDialogModel(String message, String positiveText, boolean cancellable) {
        this.message = message;
        this.positiveText = positiveText;
        this.cancellable = cancellable;
    }

    public String getNeutralText() {
        return NeutralText;
    }

    public void setNeutralText(String neutralText) {
        NeutralText = neutralText;
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

    public String getPositiveText() {
        return positiveText;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
    }

    public DialogInterface.OnClickListener getOnPositiveCLick() {
        return onPositiveCLick;
    }

    public void setOnPositiveCLick(DialogInterface.OnClickListener onPositiveCLick) {
        this.onPositiveCLick = onPositiveCLick;
    }

    public String getNegativeText() {
        return negativeText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
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