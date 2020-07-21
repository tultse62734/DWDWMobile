package com.example.dwdwproject.repositories.locationRepositories;

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

public interface ServiceLocation {
    @GET(ConfigAPI.Api.GETALLLOCATION)
    Call<ResponseBody> getAllLocation(@HeaderMap Map<String, String> map);

    @GET(ConfigAPI.Api.GETLOCATIONBYID)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> getLocationById(@HeaderMap Map<String, String> map,@Query("locationId") int id);

    @POST(ConfigAPI.Api.CREATELOCATION)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> createLocation(@HeaderMap Map<String, String> map,@Body RequestBody mRequestBody);

    @PUT(ConfigAPI.Api.UPDATELOCATION)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> updateLocation(@HeaderMap Map<String, String> map,@Body RequestBody mRequestBody);

    @PUT(ConfigAPI.Api.DEACTIVELOCATION)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> deactiveLocation(@HeaderMap Map<String, String> map,@Path("locationId") int id);
    @PUT(ConfigAPI.Api.ACTIVELOCATION)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> activeLocation(@HeaderMap Map<String, String> map,@Body RequestBody mRequestBody);

    @GET(ConfigAPI.Api.GETMANAGERLOCATION)
    Call<ResponseBody> getManagerLocation(@HeaderMap Map<String, String> map);
}
