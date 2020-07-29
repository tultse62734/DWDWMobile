package com.example.dwdwproject.repositories.shiftRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.models.Shift;
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

public class ShiftRepositoriesImpl implements ShiftRepositories {
    @Override
    public void getAllShift(final Context mContext, String token,final CallBackData<List<ShiftDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesShift().getAllShift(map);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);

        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<List<ShiftDTO>>() {

                        }.getType();
                        //call response to get value data
                        List<ShiftDTO>mShiftList = new Gson().fromJson(result, type);
                        mCallBackData.onSucess(mShiftList);

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
    public void getShiftByManager(final Context mContext, String token,final CallBackData<List<ShiftDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesShift().getShiftManager(map);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<List<ShiftDTO>>() {

                        }.getType();
                        //call response to get value data
                        List<ShiftDTO>mShiftList = new Gson().fromJson(result, type);
                        mCallBackData.onSucess(mShiftList);

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
    public void createShift(final Context context, String token, int locationId ,ShiftDTO mShift, final CallBackData<ShiftDTO> CallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject =  new JSONObject();
        try {
            jsonObject.put("workerID",mShift.getWorkerId());
            jsonObject.put("date",mShift.getDate());
            jsonObject.put("roomId",mShift.getRoomId());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesShift().createShift(map,locationId,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ShiftDTO>() {
                        }.getType();
                        //call response to get value data
                        ShiftDTO mShiftDTO = new Gson().fromJson(result, type);
                        CallBackData.onSucess(mShiftDTO);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    CallBackData.onFail(response.message());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                KProgressHUDManager.dismiss(context, khub);
                CallBackData.onFail(t.getMessage());
            }
        });
    }
    @Override
    public void updateShift(final Context mContext,String token,int locationId,ShiftDTO mShift,final CallBackData<ShiftDTO> callBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject =  new JSONObject();
        try {
            jsonObject.put("shiftId",mShift.getShiftId());
            jsonObject.put("workerID",mShift.getWorkerId());
            jsonObject.put("date",mShift.getDate());
            jsonObject.put("roomId",mShift.getRoomId());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesShift().updateShift(map,locationId,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ShiftDTO>() {

                        }.getType();
                        //call response to get value data
                        ShiftDTO mShiftDTO = new Gson().fromJson(result, type);
                        callBackData.onSucess(mShiftDTO);

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
    public void updateShiftActive(final Context mContext,String token,ShiftDTO mShift,final CallBackData<ShiftDTO> callBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject =  new JSONObject();
        try {
            jsonObject.put("shiftId",mShift.getShiftId());
            jsonObject.put("isActive",mShift.isActive());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesShift().updateShiftActive(map,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ShiftDTO>() {

                        }.getType();
                        //call response to get value data
                        ShiftDTO mShiftDTO = new Gson().fromJson(result, type);
                        callBackData.onSucess(mShiftDTO);

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
    public void getShiftFromLocation(final Context mContext, String token, int locationId, String date,final CallBackData<List<ShiftDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesShift().getShiftFromLocation(map,locationId,date);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<List<ShiftDTO>>() {
                        }.getType();
                        //call response to get value data
                        List<ShiftDTO> mShiftDTO = new Gson().fromJson(result, type);
                        mCallBackData.onSucess(mShiftDTO);

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
    public void getShiftFromLocationByWorker(final Context mContext, String token, int locationId, String date,final CallBackData<List<ShiftDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesShift().getShiftWorker(map,locationId,date);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<List<ShiftDTO>>() {
                        }.getType();
                        //call response to get value data
                        List<ShiftDTO> mShiftDTO = new Gson().fromJson(result, type);
                        mCallBackData.onSucess(mShiftDTO);

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
