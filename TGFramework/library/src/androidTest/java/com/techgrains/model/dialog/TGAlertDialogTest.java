package com.techgrains.model.dialog;

import android.content.DialogInterface;

import junit.framework.Assert;
import junit.framework.TestCase;


/**
 * Created by CNS on 21/04/15.
 */
public class TGAlertDialogTest extends TestCase {

    TGAlertDialog model;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateObject() {
        String title = "Title";
        String message = "message";
        String positiveText = "positiveText";
        DialogInterface.OnClickListener onPositiveCLick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        String negativeText = "negativeText";
        DialogInterface.OnClickListener onNegativeClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        boolean cancellable = false;

        model = new TGAlertDialog(title, message, positiveText, onPositiveCLick, negativeText, onNegativeClick, cancellable);

        Assert.assertTrue(model.getTitle().equals(title));
        Assert.assertTrue(model.getMessage().equals(message));
        Assert.assertTrue(model.getPositiveButtonText().equals(positiveText));
        Assert.assertEquals(onPositiveCLick, model.getOnPositiveCLick());

        Assert.assertTrue(model.getNegativeButtonText().equals(negativeText));
        Assert.assertEquals(onNegativeClick, model.getOnNegativeClick());

        Assert.assertTrue(model.isCancellable() == cancellable);
    }
}
