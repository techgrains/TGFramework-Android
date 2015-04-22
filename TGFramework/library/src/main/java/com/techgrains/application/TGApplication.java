package com.techgrains.application;

import android.app.Application;

import com.techgrains.session.TGSession;

/**
 * Created by CNS on 22/04/15.
 */
public class TGApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TGSession.instance().setApplicationContext(this.getApplicationContext());
    }
}
