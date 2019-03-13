package com.ibt.niramaya.utils;

import android.app.Application;

public class CustomFontApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "font/Raleway-Regular.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "font/Raleway-Medium.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "font/Raleway-Bold.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "font/Raleway-Regular.ttf");
    }
}
