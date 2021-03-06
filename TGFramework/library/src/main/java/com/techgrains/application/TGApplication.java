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
package com.techgrains.application;

import android.app.Application;
import android.content.Context;

/**
 * Application which holds context reference.
 * - Android framework manages Application as Singleton Instance internally.
 * - TGFramework uses this TGApplication for Application Context reference.
 */
public class TGApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }

    /**
     * Returns Application Context
     *
     * @return Context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * Sets Application Context
     * @param context Context
     */
    public static void setContext(Context context) {
        TGApplication.context = context;
    }
}
