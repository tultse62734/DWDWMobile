package com.example.dwdwproject.repositories.devicesRepositories;

import com.example.dwdwproject.utils.ConfigAPI;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceDevice {
    @GET(ConfigAPI.Api.GETALLDEVICE)
    Call<ResponseBody> getAllDevice();

    @GET(ConfigAPI.Api.GETDEVICEBYID)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> getDeviceById(@Path("id") int id);

    @GET(ConfigAPI.Api.GETALLDEVICEFROMLOCATION)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> getDeviceByLocationId(@Path("id") int id);

    @POST(ConfigAPI.Api.CREATEDEVICE)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> createDevice(@Body RequestBody mRequestBody);

    @PUT(ConfigAPI.Api.UPDATEDEVICE)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> updateDevice(@Path("id") int id,@Body RequestBody mRequestBody);
}
