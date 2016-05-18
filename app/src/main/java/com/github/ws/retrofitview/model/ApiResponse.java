package com.github.ws.retrofitview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 */
public class ApiResponse<T> {

    @SerializedName("Code")
    public int code;

    @SerializedName("ServerTime")
    public String serverTime;

    @SerializedName("List")
    public List<T> categoryList;
}
