package com.example.dwdwproject.repositories;


import com.example.dwdwproject.utils.ConfigAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {
    @POST(ConfigAPI.Api.LOGIN)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> login(@Path("username") String username,@Path("username") String password);
}
