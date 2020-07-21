package com.example.dwdwproject.repositories;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LoginDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.models.Device;
import com.example.dwdwproject.models.ReponseDTO;
import com.example.dwdwproject.models.User;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.utils.ClientApi;
import com.example.dwdwproject.utils.KProgressHUDManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
public class DWDWRepositoriesImpl implements DWDWRepositories {
    @Override
    public void Login(final Context mContext, LoginDTO mLoginDTO, final CallBackData<ReponseDTO> callBackData) {
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject =  new JSONObject();
        try {
            jsonObject.put("userName",mLoginDTO.getUsername());
            jsonObject.put("password",mLoginDTO.getPassword());
            jsonObject.put("deviceToken",mLoginDTO.getDeviceToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
        Call<ResponseBody> mBodyCall = clientApi.Services().login(body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ReponseDTO>() {

                        }.getType();
                        //call response to get value data
                        ReponseDTO reponseDTO = new Gson().fromJson(result, type);
                        callBackData.onSucess(reponseDTO);

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
    public void Login2(final Context mContext, LoginDTO mLoginDTO,final CallBackData<String> callBackData) {
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject =  new JSONObject();
        try {
            jsonObject.put("username",mLoginDTO.getUsername());
            jsonObject.put("password",mLoginDTO.getPassword());
            jsonObject.put("deviceToken",mLoginDTO.getDeviceToken());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());

        Call<ResponseBody> mBodyCall = clientApi.Services().login(body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);

        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        callBackData.onSucess(result);

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
    public void GetUsetInfor(final Context context, String token, final CallBackData<UserDTO> mCallBackData) {
        ClientApi clientApi = new ClientApi();
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        Call<ResponseBody> mBodyCall = clientApi.Services().getUserInfor(map);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<UserDTO>() {

                        }.getType();
                        //call response to get value data
                        UserDTO userDTO = new Gson().fromJson(result, type);
                        System.out.println(userDTO.getUserName().toString());
                        mCallBackData.onSucess(userDTO);

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
    public void UpdateAccout(final Context context, String token, UserDTO mUserDTO, final CallBackData<UserDTO> mCallBackData) {
        ClientApi clientApi = new ClientApi();
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        JSONObject jsonObject =  new JSONObject();
        try {
            jsonObject.put("password",mUserDTO.getPassword());
            jsonObject.put("phone",mUserDTO.getGender());
            jsonObject.put("dateOfBirth",mUserDTO.getDateOfBirth());
            jsonObject.put("gender",mUserDTO.getGender());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
        Call<ResponseBody> mBodyCall = clientApi.Services().updateInfo(map,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<UserDTO>() {

                        }.getType();
                        //call response to get value data
                        UserDTO userDTO = new Gson().fromJson(result, type);
                        System.out.println(userDTO.getUserName().toString());
                        mCallBackData.onSucess(userDTO);

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
}
