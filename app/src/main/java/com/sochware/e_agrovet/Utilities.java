package com.sochware.e_agrovet;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class Utilities {
    public static void log(String string) {
        if (BuildConfig.DEBUG) {

            Log.i("Utils Log", string);
        }
    }

    public static void toast(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    public static String getImageUrl(String name) {
        return "";
    }

    public static void openInMap(Context context, String lat, String lng, String name) {
        Utilities.log("Lat Lng" + lat + " " + lng);
        try {
            Uri gmmIntentUri = Uri.parse(String.format("geo:%s,%s?q=%s,%s(%s)", lat, lng, lat, lng, Uri.encode(name)));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(mapIntent);
            }
        } catch (Exception e) {
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
