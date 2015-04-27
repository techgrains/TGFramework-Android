package com.techgrains.example;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.techgrains.dialog.TGIAlertDialog;
import com.techgrains.dialog.TGIProgressDialog;

import junit.framework.Assert;

/**
 * Created by CNS on 27/04/15.
 */
public class FeaturesActivityTest extends ActivityInstrumentationTestCase2<FeaturesActivity> {

    private FeaturesActivity mActivity = null;

    private Button btnSignUp = null, btnLogIn = null;

    public FeaturesActivityTest() {
        super(FeaturesActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mActivity = getActivity();

    }

    public void testTGIAlertDialog_NotNull() throws Exception {

        try {
            Assert.assertNotNull("Activity do not implement TGIAlertDialog", (TGIAlertDialog) mActivity);
        } catch (Exception e) {
            Assert.assertTrue("Implement TGIAlertDialog in TGActivity", false);

        }

    }

    public void testTGIProgressDialog_NotNull() throws Exception {

        try {
            Assert.assertNotNull("Activity do not implement TGIProgressDialog", (TGIProgressDialog) mActivity);
        } catch (Exception e) {
            Assert.assertTrue("Implement TGIProgressDialog in TGActivity", false);

        }

    }

}
