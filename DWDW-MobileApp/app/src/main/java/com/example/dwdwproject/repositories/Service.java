package com.example.dwdwproject.repositories;


import com.example.dwdwproject.utils.ConfigAPI;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Service {
    @POST(ConfigAPI.Api.LOGIN)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> login(@Body RequestBody mRequestBody);

    @GET(ConfigAPI.Api.GETUSERINFOR)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> getUserInfor(@HeaderMap Map<String, String> map);

    @GET(ConfigAPI.Api.NOTIFY)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> getMessgae(@HeaderMap Map<String, String> map);

    @PUT(ConfigAPI.Api.UPDATEINFOR)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> updateInfo(@HeaderMap Map<String, String> map,@Body RequestBody mRequestBody);

}
