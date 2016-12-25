package com.example.chino.courselabasico;

import android.app.Application;
import android.content.Context;

/**
 * Created by FAMILY on 25/12/2016.
 */

public class NoteApplication extends Application
{
    private static Context context;

    public void onCreate()
    {
        super.onCreate();
        NoteApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return NoteApplication.context;
    }
}
