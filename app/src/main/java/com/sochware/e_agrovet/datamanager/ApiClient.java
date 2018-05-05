package com.sochware.e_agrovet.datamanager;


import com.google.gson.GsonBuilder;
import com.sochware.e_agrovet.MainApplication;
import com.sochware.e_agrovet.Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://52.224.13.239/agrovet/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        File httpCacheDirectory = new File(MainApplication.context.getCacheDir(), ".agrovet");
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        OkHttpClient client = new OkHttpClient.Builder()
//                .cache(cache)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                            .serializeNulls()
                            .create()))
                    .build();
        }
        return retrofit;
    }


    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (Utilities.isNetworkConnected(MainApplication.context)) {
                int maxAge = 60;//*6;
                CacheControl cacheControl = new CacheControl.Builder().maxAge(maxAge, TimeUnit.MINUTES).build();
                Utilities.log("Cache control " + cacheControl.toString());
                return originalResponse.newBuilder()
                        .header("Cache-Control",/* "public, max-age=" + maxAge */cacheControl.toString())
                        .build();
            } else {
                int maxStale = 1000;
                CacheControl cacheControl = new CacheControl.Builder().onlyIfCached().maxStale(maxStale, TimeUnit.DAYS).build();
                Utilities.log("Cache control " + cacheControl.toString());
                return originalResponse.newBuilder()
                        .header("Cache-Control",/* "public, only-if-cached, max-stale=" + maxStale*/ cacheControl.toString())
                        .build();
            }
        }
    };
}