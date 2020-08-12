package com.example.dwdwproject.models;

import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class ResultReponseListRoomDTO {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private List<RoomDTO> data;
    @SerializedName("message")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<RoomDTO> getData() {
        return data;
    }

    public void setData(List<RoomDTO> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
