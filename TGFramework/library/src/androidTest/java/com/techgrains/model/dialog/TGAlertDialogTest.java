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
package com.techgrains.model.dialog;

import android.content.DialogInterface;

import junit.framework.Assert;
import junit.framework.TestCase;

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

        model = new TGAlertDialog(title, message, positiveText);

        Assert.assertTrue(model.getTitle().equals(title));
        Assert.assertTrue(model.getMessage().equals(message));
        Assert.assertTrue(model.getPositiveButtonText().equals(positiveText));
        Assert.assertEquals(onPositiveCLick, model.getOnPositiveCLick());

        Assert.assertTrue(model.getNegativeButtonText().equals(negativeText));
        Assert.assertEquals(onNegativeClick, model.getOnNegativeClick());

        Assert.assertTrue(model.isCancellable() == cancellable);
    }
}
