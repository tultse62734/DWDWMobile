package com.example.dwdwproject.repositories.userRepositories;

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

public interface ServiceUsers {
    @GET(ConfigAPI.Api.GETALLUSER)
    Call<ResponseBody> getAllUser(@HeaderMap Map<String, String> map);
    @GET(ConfigAPI.Api.GETALLUSERBYADMIN)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> getAllUserByAdmin(@HeaderMap Map<String, String> map,@Query("locationId") int id);

    @GET(ConfigAPI.Api.GETALLUSERBYMANAGER)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> getAllUserByManager(@HeaderMap Map<String, String> map,@Query("locationId") int id);

    @POST(ConfigAPI.Api.CREATEUSER)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> createUser(@HeaderMap Map<String, String> map,@Body RequestBody mRequestBody);

    @PUT(ConfigAPI.Api.UPDATEUSER)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> updateUser(@HeaderMap Map<String, String> map,@Body RequestBody mRequestBody);

    @PUT(ConfigAPI.Api.DELETEUSER)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> deleteUser(@HeaderMap Map<String, String> map,@Path("locationId") int id);

    @PUT(ConfigAPI.Api.ASSGINUSER)
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> assginUser(@HeaderMap Map<String, String> map,@Body RequestBody mRequestBody);

}
