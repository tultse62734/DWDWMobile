package com.example.dwdwproject.repositories.shiftRepositories;

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
import retrofit2.http.Query;

public interface ServiceShift {
    @GET(ConfigAPI.Api.GETALLSHIFT)
    Call<ResponseBody> getAllShift(@HeaderMap Map<String, String> map);

    @GET(ConfigAPI.Api.GETSHIFTOFMANAGER)
    Call<ResponseBody> getShiftManager(@HeaderMap Map<String, String> map);

    @GET(ConfigAPI.Api.GETSHIFTOFWORKER)
    Call<ResponseBody> getShiftWorker(@HeaderMap Map<String, String> map);

    @POST(ConfigAPI.Api.CREATESHIFT)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> createShift(@HeaderMap Map<String, String> map,@Query("locationId") int locationId,@Body RequestBody mRequestBody);

    @PUT(ConfigAPI.Api.UPDATESHIFT)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> updateShift(@HeaderMap Map<String, String> map,@Query("locationID") int locationId,@Body RequestBody mRequestBody);

    @PUT(ConfigAPI.Api.UPDATESHIFTACTIVE)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> updateShiftActive(@HeaderMap Map<String, String> map,@Body RequestBody mRequestBody);
    @GET(ConfigAPI.Api.GETSHIFTFROMLOCATION)
    Call<ResponseBody> getShiftFromLocation(@HeaderMap Map<String, String> map, @Query("locationId") int locationId,@Query("date")String date);

}
