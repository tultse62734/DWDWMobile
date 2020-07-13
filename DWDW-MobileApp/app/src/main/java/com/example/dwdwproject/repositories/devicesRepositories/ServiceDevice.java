package com.example.dwdwproject.repositories.devicesRepositories;

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
import retrofit2.http.Query;

public interface ServiceDevice {
    @GET(ConfigAPI.Api.GETALLDEVICE)
    Call<ResponseBody> getAllDevice(@HeaderMap Map<String, String> map);

    @GET(ConfigAPI.Api.GETDEVICEBYID)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> getDeviceById(@HeaderMap Map<String, String> map,@Query("id") int id);

    @GET(ConfigAPI.Api.ADMIMGETALLDEVICEFROMLOCATION)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> getDeviceByLocationId(@HeaderMap Map<String, String> map,@Query("id") int id);

    @POST(ConfigAPI.Api.CREATEDEVICE)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> createDevice(@HeaderMap Map<String, String> map,@Body RequestBody mRequestBody);

    @PUT(ConfigAPI.Api.UPDATEDEVICE)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> updateDevice(@HeaderMap Map<String, String> map,@Body RequestBody mRequestBody);
}
