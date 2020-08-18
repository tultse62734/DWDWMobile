package com.example.dwdwproject.repositories;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LoginDTO;
import com.example.dwdwproject.ResponseDTOs.NotifyDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO1;
import com.example.dwdwproject.models.ReponseDTO;
import com.example.dwdwproject.models.ResultReponse;
import com.example.dwdwproject.models.ResultReponseListNotifyDTO;
import com.example.dwdwproject.models.ResultReponseUserDTO;
import com.example.dwdwproject.models.ResultReponseUserDTO1;
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
        final Call<ResponseBody> mBodyCall = clientApi.Services().login(body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                try {
                    String result = response.body().string();
                    Type type = new TypeToken<ResultReponse>() {
                    }.getType();
                    ResultReponse resultReponse = new Gson().fromJson(result,type);
                    if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                        ReponseDTO reponseDTO = new ReponseDTO();
                        reponseDTO.setToken(resultReponse.getData().get(0));
                        callBackData.onSucess(reponseDTO);
                    } else {
                        callBackData.onFail(response.message());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
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
                try {
                    String result = response.body().string();
                    Type type = new TypeToken<ResultReponseUserDTO>() {
                    }.getType();
                    ResultReponseUserDTO resultReponse = new Gson().fromJson(result,type);
                    if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {

                    } else {
                        callBackData.onFail(response.message());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
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
    public void GetUsetInfor(final Context context, String token, final CallBackData<UserDTO1> mCallBackData) {
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
                try {
                    String result = response.body().string();
                    Type type = new TypeToken<ResultReponseUserDTO1<UserDTO1>>() {
                    }.getType();
                    ResultReponseUserDTO1<UserDTO1> resultReponse = new Gson().fromJson(result,type);
                    if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                        mCallBackData.onSucess(resultReponse.getData().get(0));
                    } else {
                        mCallBackData.onFail(resultReponse.getMessage());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
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
    public void UpdateAccout(final Context context, String token, UserDTO1 mUserDTO, final CallBackData<UserDTO1> mCallBackData) {
        ClientApi clientApi = new ClientApi();
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        JSONObject jsonObject =  new JSONObject();
        try {
            jsonObject.put("fullName",mUserDTO.getFullName());
            jsonObject.put("phone",mUserDTO.getPhone());
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
                try {
                    String result = response.body().string();
                    Type type = new TypeToken<ResultReponseUserDTO1<UserDTO1>>() {
                    }.getType();
                    ResultReponseUserDTO1<UserDTO1> resultReponse = new Gson().fromJson(result,type);
                    if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                        mCallBackData.onSucess(resultReponse.getData().get(0));
                    } else {
                        mCallBackData.onFail(resultReponse.getMessage());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
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
    public void getNotify(final Context context, String token, final CallBackData<List<NotifyDTO>> mCallBackData) {
        ClientApi clientApi = new ClientApi();
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        Call<ResponseBody> mBodyCall = clientApi.Services().getMessgae(map);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                try {
                    String result = response.body().string();
                    Type type = new TypeToken<ResultReponseListNotifyDTO>() {
                    }.getType();
                    ResultReponseListNotifyDTO resultReponse = new Gson().fromJson(result,type);
                    if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                        mCallBackData.onSucess(resultReponse.getData());
                    } else {
                        mCallBackData.onFail(resultReponse.getMessage());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
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
