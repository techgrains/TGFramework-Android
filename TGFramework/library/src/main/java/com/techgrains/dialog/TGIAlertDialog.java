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
package com.techgrains.dialog;

import com.techgrains.model.dialog.TGAlertDialog;

/**
 * Custom alert dialog interface to manage custom callback methods related to alert dialog.
 */
public interface TGIAlertDialog {

    /**
     * Shows Alert Dialog based on provided attributes in param object
     *
     * @param alertDialog TGAlertDialog
     */
    public void showAlertDialog(TGAlertDialog alertDialog);

}
