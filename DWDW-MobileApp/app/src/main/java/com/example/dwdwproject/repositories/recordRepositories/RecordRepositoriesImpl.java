package com.example.dwdwproject.repositories.recordRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LocationRecord;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.utils.ClientApi;
import com.example.dwdwproject.utils.KProgressHUDManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordRepositoriesImpl implements RecordRepositories {
    @Override
    public void getAllRecord(Context context, String token, CallBackData<RecordDTO> mCallBackData) {
    }
    @Override
    public void getRecordDetailById(Context context, String token, int recordID, CallBackData<RecordDTO> mCallBackData) {
    }
    @Override
    public void getRecordByLocationId(final Context context, String token, int locationId, final CallBackData<List<RecordDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServiceRecord().getRecordsByLocationId(map,locationId);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<List<RecordDTO>>() {

                        }.getType();
                        //call response to get value data
                        List<RecordDTO> mRecordDTOS = new Gson().fromJson(result, type);
                        mCallBackData.onSucess(mRecordDTOS);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    mCallBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                KProgressHUDManager.dismiss(context, khub);
                mCallBackData.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getLocationRecord(final Context context, String token,String startDate,String endDate, final CallBackData<List<LocationRecord>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServiceRecord().getLocationRecord(map,startDate,endDate);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<List<LocationRecord>>() {

                        }.getType();
                        //call response to get value data
                        List<LocationRecord> mRecordDTOS = new Gson().fromJson(result, type);
                        mCallBackData.onSucess(mRecordDTOS);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    mCallBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                KProgressHUDManager.dismiss(context, khub);
                mCallBackData.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getRecordsByLocationIdAndTime(final Context mContext, String token, int locationID, String start, String end, final CallBackData<List<RecordDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServiceRecord().getRecordsByLocationIdAndTime(map, locationID, start, end);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<List<RoomDTO>>() {

                        }.getType();
                        //call response to get value data
                        List<RecordDTO> mRecordDTOS = new Gson().fromJson(result, type);
                        mCallBackData.onSucess(mRecordDTOS);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    mCallBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                KProgressHUDManager.dismiss(mContext, khub);
                mCallBackData.onFail(t.getMessage());
            }
        });
    }
}
