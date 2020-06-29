package com.example.dwdwproject.repositories;

import android.content.Context;

import com.example.dwdwproject.models.Device;
import com.example.dwdwproject.models.ReponseDTO;
import com.example.dwdwproject.models.User;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.utils.ClientApi;
import com.example.dwdwproject.utils.KProgressHUDManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DWDWRepositoriesImpl implements DWDWRepositories {
    @Override
    public void Login(final Context mContext, String username, String password, final CallBackData<ReponseDTO> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.Services().login(username,password);
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
}
