package com.example.dwdwproject.repositories.locationRepositories;

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

public interface ServiceLocation {
    @GET(ConfigAPI.Api.GETALLLOCATION)
    Call<ResponseBody> getAllLocation();

    @GET(ConfigAPI.Api.GETLOCATIONBYID)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> getLocationById(@Path("id") int id);

    @POST(ConfigAPI.Api.CREATELOCATION)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> createLocation(@Body RequestBody mRequestBody);

    @PUT(ConfigAPI.Api.UPDATELOCATION)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> updateLocation(@Path("id") int id,@Body RequestBody mRequestBody);
}
