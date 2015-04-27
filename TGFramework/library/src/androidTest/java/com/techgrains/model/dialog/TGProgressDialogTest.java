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

import junit.framework.Assert;
import junit.framework.TestCase;

public class TGProgressDialogTest extends TestCase {

    TGProgressDialog model;

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
        boolean isCancelable = true;
        int drawable = android.R.drawable.btn_default;
        model = new TGProgressDialog(title, message, drawable);

        model.setCancelable(isCancelable);


        Assert.assertTrue(model.getTitle().equals(title));
        Assert.assertTrue(model.getIndeterminateDrawable() == drawable);
        Assert.assertTrue(model.getMessage().equals(message));
        Assert.assertTrue(model.isCancelable() == isCancelable);

    }
}
