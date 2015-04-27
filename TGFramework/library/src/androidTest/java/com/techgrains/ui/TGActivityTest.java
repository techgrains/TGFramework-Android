/*
 * Copyright 2015 Techgrains Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.techgrains.ui;

import android.test.ActivityInstrumentationTestCase2;

import com.techgrains.dialog.TGIAlertDialog;
import com.techgrains.dialog.TGIProgressDialog;

import junit.framework.Assert;

public class TGActivityTest extends ActivityInstrumentationTestCase2<TGActivity> {

    private TGActivity mActivity = null;


    public TGActivityTest() {
        super(TGActivity.class);
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
