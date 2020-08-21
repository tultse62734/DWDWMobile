package com.example.dwdwproject.repositories.roomRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.models.ResultReponseListRoomDTO;
import com.example.dwdwproject.models.ResultReponseListShiftDTO;
import com.example.dwdwproject.models.ResultReponseRoomDTO;
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

public class RoomRepositoriesImpl implements RoomRepositories {
    @Override
    public void getAllRoomfromLocation(final Context context, String token, int locationId, final CallBackData<List<RoomDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesRoom().getRoomByLocationId(map,locationId);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                try {
                    String result = response.body().string();
                    Type type = new TypeToken<ResultReponseListRoomDTO<List<RoomDTO>>>() {
                    }.getType();
                    ResultReponseListRoomDTO<List<RoomDTO>> resultReponse = new Gson().fromJson(result,type);
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
    public void getAllRoom(final Context context, String token, final CallBackData<List<RoomDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesRoom().getAllRoom(map);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                try {
                    String result = response.body().string();
                    Type type = new TypeToken<ResultReponseListRoomDTO<List<RoomDTO>>>() {
                    }.getType();
                    ResultReponseListRoomDTO<List<RoomDTO>> resultReponse = new Gson().fromJson(result,type);
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
    public void createRoom(final Context context, String token, RoomDTO roomDTO, final CallBackData<RoomDTO> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject =  new JSONObject();
        try {
            jsonObject.put("roomCode",roomDTO.getRoomCode());
            jsonObject.put("locationId",roomDTO.getLocationId());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesRoom().createRoom(map,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if(response.code()==200&& response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseRoomDTO<RoomDTO>>() {
                        }.getType();
                        ResultReponseRoomDTO<RoomDTO> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMessage());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(response.code()==403 && response.body()!=null){
                    mCallBackData.onFail("Foriden Authorization");
                }else if(response.code() ==500){
                    mCallBackData.onFail("Server is error");
                }
                else{
                    mCallBackData.onFail("Room is existed ");
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
    public void updateRoomById(final Context context, String token, RoomDTO mRoomDTO,final CallBackData<RoomDTO> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject =  new JSONObject();
        try {
            jsonObject.put("roomId",mRoomDTO.getRoomId());
            jsonObject.put("roomCode",mRoomDTO.getRoomCode());
            jsonObject.put("locationId",mRoomDTO.getLocationId());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesRoom().updateRoom(map,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if(response.code()==200&& response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseRoomDTO<RoomDTO>>() {
                        }.getType();
                        ResultReponseRoomDTO<RoomDTO> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMessage());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(response.code()==403 && response.body()!=null){
                    mCallBackData.onFail("Foriden Authorization");
                }else if(response.code() ==500){
                    mCallBackData.onFail("Server is error");
                }
                else{
                    mCallBackData.onFail("Room is existed ");
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
    public void getAllRoomfromLocationByManager(final Context context, String token, int locationId, final CallBackData<List<RoomDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesRoom().getRoomByLocationIdByManager(map,locationId);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                try {
                    String result = response.body().string();
                    Type type = new TypeToken<ResultReponseListRoomDTO<List<RoomDTO>>>() {
                    }.getType();
                    ResultReponseListRoomDTO<List<RoomDTO>> resultReponse = new Gson().fromJson(result,type);
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
    public void getRoomStatus(final Context context, String token, RoomDTO mRoomDTO, final CallBackData<RoomDTO> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();

        Call<ResponseBody> mBodyCall = clientApi.ServicesRoom().updateRoomStatus(map,mRoomDTO.getRoomId());
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if(response.code()==200 && response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseRoomDTO<RoomDTO>>() {
                        }.getType();
                        ResultReponseRoomDTO<RoomDTO> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMessage());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    mCallBackData.onFail("Update Room Status Fail");
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
    public void getUnassignedRoomsFromLocation( final Context context, String token, int locationId,  final CallBackData<List<RoomDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesRoom().getRoomById(map,locationId);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                try {
                    String result = response.body().string();
                    Type type = new TypeToken<ResultReponseListRoomDTO<List<RoomDTO>>>() {
                    }.getType();
                    ResultReponseListRoomDTO<List<RoomDTO>> resultReponse = new Gson().fromJson(result,type);
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
}
