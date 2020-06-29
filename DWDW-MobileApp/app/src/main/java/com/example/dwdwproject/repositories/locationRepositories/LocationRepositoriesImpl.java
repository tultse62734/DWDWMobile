package com.example.dwdwproject.repositories.locationRepositories;

import android.content.Context;

import com.example.dwdwproject.models.Device;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.utils.ClientApi;
import com.example.dwdwproject.utils.KProgressHUDManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepositoriesImpl implements LocationRepositories {
    @Override
    public void getAllLocationList(final Context mContext, final CallBackData<List<Location>> mCallBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesLocation().getAllLocation();
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);

        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<List<Location>>() {

                        }.getType();
                        //call response to get value data
                        List<Location>mDeviceList = new Gson().fromJson(result, type);
                        mCallBackData.onSucess(mDeviceList);

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

    @Override
    public void getAllLocationById(final Context mContext, int locationId,final CallBackData<Location> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesLocation().getLocationById(locationId);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);

        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<Location>() {

                        }.getType();
                        //call response to get value data
                        Location mLocation = new Gson().fromJson(result, type);
                        callBackData.onSucess(mLocation);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    callBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                KProgressHUDManager.dismiss(mContext, khub);
                callBackData.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void createLocation(final Context mContext, Location mLocation,final CallBackData<String> callBackData) {
        ClientApi clientApi = new ClientApi();
        JSONObject data = new JSONObject();

        try {
            data.put("locationId", mLocation.getLocationId());
            data.put("locationCode", mLocation.getNameLocation());
            data.put("isActive", mLocation.isStatus());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesLocation().createLocation(body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<Integer>() {

                        }.getType();
                        //call response to get value data
                        Integer reuslt = new Gson().fromJson(result, type);
                        callBackData.onSucess("Tạo Khu vực thành công");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    callBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                KProgressHUDManager.dismiss(mContext, khub);
                callBackData.onFail(t.getMessage());
            }
        });

    }

    @Override
    public void updateLocation(final Context mContext,int locationId, Location mLocation,final CallBackData<String> callBackData) {
        ClientApi clientApi = new ClientApi();
        JSONObject data = new JSONObject();

        try {
            data.put("locationId", mLocation.getLocationId());
            data.put("locationCode", mLocation.getNameLocation());
            data.put("isActive", mLocation.isStatus());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesLocation().updateLocation(locationId,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<Integer>() {

                        }.getType();
                        //call response to get value data
                        Integer reuslt = new Gson().fromJson(result, type);
                        callBackData.onSucess("Tạo Khu vực thành công");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    callBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                KProgressHUDManager.dismiss(mContext, khub);
                callBackData.onFail(t.getMessage());
            }
        });

    }
}
