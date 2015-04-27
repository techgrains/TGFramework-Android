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

import android.support.annotation.DrawableRes;

import com.techgrains.model.TGModel;

public class TGProgressDialog extends TGModel {

    private String title;
    private String message;
    private boolean cancelable = true;
    private int indeterminateDrawable = -1;

    /**
     * Progress dialog with title and message
     *
     * @param title String
     * @param message String
     */

    public TGProgressDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }

    /**
     * Progress dialog with title, message and indeterminateDrawable
     *
     * @param title String
     * @param message String
     * @param indeterminateDrawable DrawableRes
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
     * @param indeterminateDrawable DrawableRes
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