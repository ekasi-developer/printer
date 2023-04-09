package com.bluteki.printer;

import android.app.Application;

public class Context extends Application {
    private static Context instance;

    public static Context getInstance() {
        return instance;
    }

    public static android.content.Context getContext(){
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
