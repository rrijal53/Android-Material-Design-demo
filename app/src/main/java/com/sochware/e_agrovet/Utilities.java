package com.sochware.e_agrovet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Utilities {
    public static void log(String string) {
        if (BuildConfig.DEBUG) {

            Log.i("Utils Log", string);
        }
    }

    public static boolean isNetworkConnected(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null;
        /*if (ni == null) {
            return false; // There are no active networks.
		} else
			return true;*/
    }

}