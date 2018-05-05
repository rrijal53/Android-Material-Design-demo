package com.sochware.e_agrovet.datamanager;

import com.google.gson.Gson;
import com.sochware.e_agrovet.Utilities;
import com.sochware.e_agrovet.pojo.Contacts;

import java.io.IOException;
import java.util.List;

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
