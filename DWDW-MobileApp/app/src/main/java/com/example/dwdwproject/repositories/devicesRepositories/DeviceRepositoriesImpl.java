package com.example.dwdwproject.repositories.devicesRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.AssignDeviceDTO;
import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
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
    public void getAllDeviceList(final Context mContext,String token, final CallBackData<List<DeviceDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesDevice().getAllDevice(map);
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
    public void getAllDeviceById(final Context mContext,String token, int deviceId,final CallBackData<DeviceDTO> callBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesDevice().getDeviceById(map,deviceId);
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
    public void createDevice(final Context mContext, String token,DeviceDTO mDevice, final CallBackData<DeviceDTO> callBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject data = new JSONObject();
        try {
            data.put("deviceCode",mDevice.getDeviceCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesDevice().createDevice(map,body);
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
    public void updateDevice(final Context mContext,String token,DeviceDTO mDevice, final CallBackData<DeviceDTO> callBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject data = new JSONObject();
        try {
            data.put("deviceId",mDevice.getDeviceId());

            data.put("deviceCode",mDevice.getDeviceCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesDevice().updateDevice(map,body);
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
    public void getAllDeviceFromLocationByAdmin(final Context mContext,String token, int locationId, final CallBackData<List<DeviceDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesDevice().getDeviceByLocationId(map,locationId);
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
    public void getActiveDeviceFromLocationByManager(final Context mContext,String token, int locationId, final CallBackData<List<DeviceDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesDevice().getActiveDeviceByLocationManager(map,locationId);
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
    public void assginDeviceIntoRoom(final Context mContext, String token, AssignDeviceDTO mAssignDeviceDTO, final CallBackData<AssignDeviceDTO> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject data = new JSONObject();
        try {
            data.put("roomId",mAssignDeviceDTO.getRoomId());

            data.put("deviceId",mAssignDeviceDTO.getDeviceId());

            data.put("startDate",mAssignDeviceDTO.getDateStart());

            data.put("endDate",mAssignDeviceDTO.getDateEnd());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesDevice().assginDevice(map,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);

        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<AssignDeviceDTO>() {
                        }.getType();
                        //call response to get value data
                       AssignDeviceDTO  assignDeviceDTO = new Gson().fromJson(result, type);
                        mCallBackData.onSucess(assignDeviceDTO);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    mCallBackData.onFail("Dont' Assgin Device");
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
    public void updateStatusDevice(final Context mContext, String token, DeviceDTO mDeviceDTO, final CallBackData<DeviceDTO> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject =  new JSONObject();
        try {
            jsonObject.put("deviceId",mDeviceDTO.getDeviceId());
            jsonObject.put("isActive",mDeviceDTO.isActive());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesDevice().updateDeviceStatus(map,body);
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
                        DeviceDTO deviceDTO = new Gson().fromJson(result, type);
                        mCallBackData.onSucess(deviceDTO);

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
