package com.sochware.e_agrovet.datamanager;


import com.sochware.e_agrovet.pojo.Contacts;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rowsun on 5/9/17.
 */

public interface ApiInterface {


    @GET("csvjson.json")
    Call<List<Contacts>> getContacts();

    @Headers("Cache-Control: max-age=640000")
    @POST
    @Multipart
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("name") RequestBody name);

}


