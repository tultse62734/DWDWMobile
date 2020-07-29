package com.example.dwdwproject.repositories.roomRepositories;

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

public interface ServiceRoom {
    @GET(ConfigAPI.Api.GETALLROOM)
    Call<ResponseBody> getAllRoom(@HeaderMap Map<String, String> map);

    @GET(ConfigAPI.Api.GETALLROOM)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> getRoomById(@HeaderMap Map<String, String> map,@Path("id") int id);

    @GET(ConfigAPI.Api.GETROOMFROMLOCATION)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> getRoomByLocationId(@HeaderMap Map<String, String> map,@Path("locationId") int id);
    @GET(ConfigAPI.Api.GETROOMFROMLOCATIONBYMANAGER)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> getRoomByLocationIdByManager(@HeaderMap Map<String, String> map,@Path("locationId") int id);
    @POST(ConfigAPI.Api.CREATEROOM)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> createRoom(@HeaderMap Map<String, String> map,@Body RequestBody mRequestBody);

    @PUT(ConfigAPI.Api.UPDATEROOM)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> updateRoom(@HeaderMap Map<String, String> map,@Body RequestBody mRequestBody);

    @PUT(ConfigAPI.Api.UPDATEROOMSTATUS)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> updateRoomStatus(@HeaderMap Map<String, String> map,@Path("roomId") int id);
}
