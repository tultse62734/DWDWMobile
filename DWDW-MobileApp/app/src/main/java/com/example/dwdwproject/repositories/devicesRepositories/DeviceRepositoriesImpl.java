package com.example.dwdwproject.repositories.devicesRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.models.Device;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class DeviceRepositoriesImpl implements DeviceRepositories {
    @Override
    public void getAllDeviceList(final Context mContext, final CallBackData<List<DeviceDTO>> mCallBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesDevice().getAllDevice();
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);

        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<List<DeviceDTO>>() {

                        }.getType();
                        //call response to get value data
                        List<DeviceDTO>mDeviceList = new Gson().fromJson(result, type);
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
    public void getAllDeviceById(final Context mContext, int deviceId,final CallBackData<DeviceDTO> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesDevice().getDeviceById(deviceId);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);

        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<DeviceDTO>() {

                        }.getType();
                        //call response to get value data
                        DeviceDTO mDevice = new Gson().fromJson(result, type);
                        callBackData.onSucess(mDevice);

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
    public void createDevice(final Context mContext, DeviceDTO mDevice, final CallBackData<String> callBackData) {
//        String hearder = "Bearer " + accessToken;
//        Map<String, String> map = new HashMap<>();
//        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject data = new JSONObject();

        try {
            data.put("deviceCode", mDevice.getNameDevice());
            data.put("deviceStatus", mDevice.getDeviceStatus());
            data.put("isActive", mDevice.isActive());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesDevice().createDevice(body);
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
                        callBackData.onSucess("Tạo thiết bị thành công");

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
    public void updateDevice(final Context mContext, int deviceId,DeviceDTO mDevice, final CallBackData<String> callBackData) {
        ClientApi clientApi = new ClientApi();
        JSONObject data = new JSONObject();

        try {
            data.put("deviceCode", mDevice.getNameDevice());
            data.put("deviceStatus", mDevice.getDeviceStatus());
            data.put("isActive", mDevice.isActive());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesDevice().updateDevice(deviceId,body);
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
                        callBackData.onSucess("Cập nhật thiết bị thành công");

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
    public void getAllDeviceFromLocation(final Context mContext, int locationId, final CallBackData<List<DeviceDTO>> mCallBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesDevice().getDeviceByLocationId(locationId);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);

        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<List<DeviceDTO>>() {

                        }.getType();
                        //call response to get value data
                        List<DeviceDTO>mDeviceList = new Gson().fromJson(result, type);
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
}
