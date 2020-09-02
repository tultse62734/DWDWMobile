package com.example.dwdwproject.repositories.recordRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.ConfirmReasonDTO;
import com.example.dwdwproject.ResponseDTOs.LocationRecord;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.models.ResultReponnseListLocationRecordDTO;
import com.example.dwdwproject.models.ResultReponseConfirmRecorDTO;
import com.example.dwdwproject.models.ResultReponseListRecordDTO;
import com.example.dwdwproject.models.ResultReponseRecordDTO;
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

public class RecordRepositoriesImpl implements RecordRepositories {
    @Override
    public void getAllRecord(Context context, String token, CallBackData<RecordDTO> mCallBackData) {
    }
    @Override
    public void getRecordDetailById(final Context context, String token, int recordID, final CallBackData<RecordDTO> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServiceRecord().getRecordsById(map,recordID);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if(response.code()==200&& response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseRecordDTO<RecordDTO>>() {
                        }.getType();
                        ResultReponseRecordDTO<RecordDTO> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMessage());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(response.code()==403 && response.body()!=null){
                    mCallBackData.onFail("Foriden Authorization");
                }else if(response.code() ==500){
                    mCallBackData.onFail("Server is error");
                }
                else{
                    mCallBackData.onFail("Record is not found");
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
                if(response.code()==200 && response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseListRecordDTO<List<RecordDTO>>>() {
                        }.getType();
                        ResultReponseListRecordDTO<List<RecordDTO>> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMesssage());
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
                    mCallBackData.onFail("Record is not found");
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
                if(response.code()==200 && response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponnseListLocationRecordDTO<List<LocationRecord>>>() {
                        }.getType();
                        ResultReponnseListLocationRecordDTO<List<LocationRecord>> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMessgae());
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
                    mCallBackData.onFail("Record is not found");
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
                if(response.code()==200 && response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseListRecordDTO<List<RecordDTO>>>() {
                        }.getType();
                        ResultReponseListRecordDTO<List<RecordDTO>> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMesssage());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mCallBackData.onFail("Location has no device");
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                KProgressHUDManager.dismiss(mContext, khub);
                mCallBackData.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getRecordByWorkerDate(final Context context, String token,int workerID, String date,final CallBackData<List<RecordDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServiceRecord().getRecordsByWorkerDate(map,workerID,date);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if(response.code()==200 && response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseListRecordDTO<List<RecordDTO>>>() {
                        }.getType();
                        ResultReponseListRecordDTO<List<RecordDTO>> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMesssage());
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
                    mCallBackData.onFail("Record is not found");
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
    public void getRecordByWorker(final Context context, String token, int locationId,String date,final CallBackData<List<RecordDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServiceRecord().getRecordsByWorker(map,date,locationId);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if(response.code()==200 && response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseListRecordDTO<List<RecordDTO>>>() {
                        }.getType();
                        ResultReponseListRecordDTO<List<RecordDTO>> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMesssage());
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
                    mCallBackData.onFail("Record is not found");
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
    public void getConfirmRecordByWorker(final Context context, String token, int locationId, String date, final CallBackData<List<RecordDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServiceRecord().getConfirmRecordByWorker(map,locationId,date);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if(response.code()==200 && response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseListRecordDTO<List<RecordDTO>>>() {
                        }.getType();
                        ResultReponseListRecordDTO<List<RecordDTO>> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMesssage());
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
                    mCallBackData.onFail("There's no record  at this date");
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
    public void getUnknowRecordByWorker(final Context context, String token, int locationId, String date, final CallBackData<List<RecordDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServiceRecord().getUnknowRecordByWorker(map,locationId,date);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if(response.code()==200 && response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseListRecordDTO<List<RecordDTO>>>() {
                        }.getType();
                        ResultReponseListRecordDTO<List<RecordDTO>> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMesssage());
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
                    mCallBackData.onFail("There's no record  at this date");
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
    public void confirmRecordByWorker(final Context context, String token, ConfirmReasonDTO confirmReasonDTO,final CallBackData<ConfirmReasonDTO> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        JSONObject data = new JSONObject();
        try {
            data.put("recordId",confirmReasonDTO.getRecordId());

            data.put("recordDateTime",confirmReasonDTO.getRecordDateTime());

            data.put("comment",confirmReasonDTO.getComment());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data.toString());
        Call<ResponseBody> mBodyCall = clientApi.ServiceRecord().confirmRecordByWorker(map,body);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if(response.code()==200 && response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseConfirmRecorDTO<ConfirmReasonDTO>>() {
                        }.getType();
                        ResultReponseConfirmRecorDTO<ConfirmReasonDTO> resultReponse = new Gson().fromJson(result,type);
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
                    mCallBackData.onFail("Confirm failed");
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
    public void getSleppRecordByManager(final Context context, String token, int workerId, int locationId, String date,final CallBackData<List<RecordDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServiceRecord().getSleepRecordByManager(map,workerId,locationId,date);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if(response.code()==200 && response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseListRecordDTO<List<RecordDTO>>>() {
                        }.getType();
                        ResultReponseListRecordDTO<List<RecordDTO>> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMesssage());
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
                    mCallBackData.onFail("There's no record  at this date");
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
    public void getDeniedRecordByManager(final Context context, String token, int workerId, int locationId, String date, final CallBackData<List<RecordDTO>> mCallBackData) {
        String hearder = "Bearer " + token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> mBodyCall = clientApi.ServiceRecord().getDeniedRecordByManager(map,workerId,locationId,date);
        final KProgressHUD khub = KProgressHUDManager.showProgressBar(context);
        mBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KProgressHUDManager.dismiss(context, khub);
                if(response.code()==200 && response.body()!=null){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultReponseListRecordDTO<List<RecordDTO>>>() {
                        }.getType();
                        ResultReponseListRecordDTO<List<RecordDTO>> resultReponse = new Gson().fromJson(result,type);
                        if (resultReponse.getStatusCode() == 200 &&resultReponse.getData() != null) {
                            mCallBackData.onSucess(resultReponse.getData().get(0));
                        } else {
                            mCallBackData.onFail(resultReponse.getMesssage());
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
                    mCallBackData.onFail("There's no record  at this date");
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
