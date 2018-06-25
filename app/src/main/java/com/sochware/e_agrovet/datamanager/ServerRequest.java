package com.sochware.e_agrovet.datamanager;

import com.google.gson.Gson;
import com.sochware.e_agrovet.Utilities;
import com.sochware.e_agrovet.pojo.Contacts;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServerRequest {
    private ApiInterface apiService;


    public interface GetContactCallback {
        void onSuccess(List<Contacts> res);

        void onError(String error);
    }


    public ServerRequest() {
    }


    public void postImage(String image_path, String name) {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        File f = new File(image_path);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), f);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", f.getName(), reqFile);
        RequestBody n =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, name);

        Call<ResponseBody> call = apiService.postImage(body, n);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void getContacts(GetContactCallback callback) {
        apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Contacts>> call = apiService.getContacts();
        call.enqueue(new Callback<List<Contacts>>() {
            @Override
            public void onResponse(Call<List<Contacts>> call, Response<List<Contacts>> response) {
                Utilities.log("Mes " + response.message());
                if (response.body() == null) {
                    callback.onError("Connection failed.");
                } else {

                    callback.onSuccess(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Contacts>> call, Throwable t) {
                t.printStackTrace();
                callback.onError("Connection failed.");
            }
        });

    }
}
