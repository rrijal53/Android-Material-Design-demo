package com.sochware.e_agrovet;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;

/**
 * Created by rowsun on 10/7/16.
 */

public class MainApplication extends Application {

    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;


    }

}
