package com.example.dwdwproject.repositories.recordRepositories;

import com.example.dwdwproject.utils.ConfigAPI;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;

public interface ServiceRecord {
    @GET(ConfigAPI.Api.GETRECORDSBYLOCATIONIDANDTIME)
    Call<ResponseBody> getRecordsByLocationIdAndTime(@HeaderMap Map<String, String> map,@Path("locationId ") int id,
                                                     @Path("start ") String start,@Path("end ") String end);
}
