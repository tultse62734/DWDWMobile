package com.example.dwdwproject.repositories.userRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.AssignDeviceDTO;
import com.example.dwdwproject.ResponseDTOs.AssignUserDTO;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.ResultReponseAssignUserDTO;
import com.example.dwdwproject.models.ResultReponseListUserDTO;
import com.example.dwdwproject.models.ResultReponseUserDTO;
import com.example.dwdwproject.models.User;
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

public class UserRepositotiesImpl implements UserRepositories {
    @Override
    public void searchUserId(final Context context, String token,int userId, final CallBackData<UserDTO> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesUsers().searchUserByAdmin(map,userId);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if(response.code()==200 && response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseUserDTO<UserDTO>>() {
                        }.getType();
                        ResultReponseUserDTO<UserDTO> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getErrorMessage());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    mCallBackData.onFail("User is not found");
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
    public void deassginUserToLocatioṇ(final Context context, String token, int userId, int locationId, final CallBackData<AssignUserDTO> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject data = new JSONObject();
        try {
            data.put("userId",userId);

            data.put("locationId",locationId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesUsers().deassginUser(map,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                try {
                    String result = response.body().string();
                    Type type = new TypeToken<ResultReponseAssignUserDTO<AssignUserDTO>>() {
                    }.getType();
                    ResultReponseAssignUserDTO<AssignUserDTO> resultReponse = new Gson().fromJson(result,type);
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
    public void getAll(final Context mContext, String token,final CallBackData<List<UserDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesUsers().getAllUser(map);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                try {
                    String result = response.body().string();
                    Type type = new TypeToken<ResultReponseListUserDTO<List<UserDTO>>>() {
                    }.getType();
                    ResultReponseListUserDTO<List<UserDTO>> resultReponse = new Gson().fromJson(result,type);
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
                KProgressHUDManager.dismiss(mContext, khub);
                mCallBackData.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getAllUserFromLocationByAdmin(final Context mContext, String token, int locationId,final CallBackData<List<UserDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesUsers().getAllUserByAdmin(map,locationId);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);

        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                try {
                    String result = response.body().string();
                    Type type = new TypeToken<ResultReponseListUserDTO<List<UserDTO>>>() {
                    }.getType();
                    ResultReponseListUserDTO<List<UserDTO>> resultReponse = new Gson().fromJson(result,type);
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
                KProgressHUDManager.dismiss(mContext, khub);
                mCallBackData.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getAllWorkerFromLocationByManager(final Context mContext, String token, int locationId,final CallBackData<List<UserDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServicesUsers().getAllWorkerByManager(map,locationId);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                try {
                    String result = response.body().string();
                    Type type = new TypeToken<ResultReponseListUserDTO<List<UserDTO>>>() {
                    }.getType();
                    ResultReponseListUserDTO<List<UserDTO>> resultReponse = new Gson().fromJson(result,type);
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
                KProgressHUDManager.dismiss(mContext, khub);
                mCallBackData.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void createUser(final Context mContext, String token, UserDTO mUserDTO,final CallBackData<UserDTO> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject data = new JSONObject();
        try {
            data.put("userName",mUserDTO.getUserName());

            data.put("password",mUserDTO.getPassword());
            data.put("fullName",mUserDTO.getFullname());
            data.put("phone",mUserDTO.getPhone());

            data.put("dateOfBirth",mUserDTO.getDateOfBirth());
            data.put("gender",mUserDTO.getGender());
            data.put("roleId",mUserDTO.getmRole().getRoleId());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesUsers().createUser(map,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if(response.code()==200&&response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseUserDTO<UserDTO>>() {
                        }.getType();
                        ResultReponseUserDTO<UserDTO> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getErrorMessage());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    mCallBackData.onFail("User is existed");
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
    public void updateUserById(final Context mContext, String token, UserDTO mUserDTO, final CallBackData<UserDTO> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject data = new JSONObject();
        try {
            data.put("userId",mUserDTO.getUserId());
            data.put("fullName",mUserDTO.getFullname());
            data.put("phone",mUserDTO.getPhone());
            data.put("dateOfBirth",mUserDTO.getDateOfBirth());
            data.put("gender",mUserDTO.getGender());
            data.put("roleId",mUserDTO.getmRole().getRoleId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesUsers().updateUser(map,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(mContext, khub);
                if(response.code()==200&& response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseUserDTO<UserDTO>>() {
                        }.getType();
                        ResultReponseUserDTO<UserDTO> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getErrorMessage());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    mCallBackData.onFail("User is existed");
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
    public void deleteUserById(Context context, String token, int userId, CallBackData<String> mCallBackData) {
    }
    @Override
    public void assignUserToLocation(final Context context, String token, AssignUserDTO assignUserDTO,final CallBackData<AssignUserDTO> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject data = new JSONObject();
        try {
            data.put("userId",assignUserDTO.getUserId());

            data.put("locationId",assignUserDTO.getLocationId());

            data.put("startDate",assignUserDTO.getStartDate());

            data.put("endDate",assignUserDTO.getEndDate());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesUsers().assginUser(map,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if(response.code()==200&&response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseAssignUserDTO<AssignUserDTO>>() {
                        }.getType();
                        ResultReponseAssignUserDTO<AssignUserDTO> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMessage());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    mCallBackData.onFail("Assign User is fail");
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
    public void updateUserStatus(final Context mContext, String token, int userId, boolean isActive,final CallBackData<UserDTO> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject data = new JSONObject();
        try {
            data.put("userId",userId);
            data.put("isActive",isActive);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServicesUsers().updateUserStatus(map,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(mContext);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code()==200 && response.body()!=null){
                    KProgressHUDManager.dismiss(mContext, khub);
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<UserDTO>() {
                        }.getType();
                        UserDTO userDTO= new Gson().fromJson(result,type);
                        mCallBackData.onSucess(userDTO);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    mCallBackData.onFail("Update Status fail");
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
