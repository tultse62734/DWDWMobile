package com.example.dwdwproject.repositories.recordRepositories;

import com.example.dwdwproject.utils.ConfigAPI;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceRecord {

    @GET(ConfigAPI.Api.GETRECORDSBYLOCATIONIDANDTIME)
    Call<ResponseBody> getRecordsByLocationIdAndTime(@HeaderMap Map<String, String> map,@Query("LocationId") int id,
                                                     @Query("StartDate") String start,@Query("EndDate") String end);
    @GET(ConfigAPI.Api.GETRECORDBYLOCATION)
    Call<ResponseBody> getRecordsByLocationId(@HeaderMap Map<String, String> map,@Query("locationId") int id);

    @GET(ConfigAPI.Api.GETRECORDBYWORKERDATE)
    Call<ResponseBody> getRecordsByWorkerDate(@HeaderMap Map<String, String> map,@Query("workerID") int id,@Query("date")String date);


    @GET(ConfigAPI.Api.GETLOCATIONRECORD)
    Call<ResponseBody> getLocationRecord(@HeaderMap Map<String, String> map,@Query("startDate")String startDate
    ,@Query("endDate")String endDate);
    @GET(ConfigAPI.Api.GETRECORDBYID)
    Call<ResponseBody> getRecordsById(@HeaderMap Map<String, String> map,@Query("recordId") int id);

}
