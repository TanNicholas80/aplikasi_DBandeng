package com.example.dbandeng.response;

import com.google.gson.annotations.SerializedName;

public class LogoutMitraRes {
    @SerializedName("response")
    private String res;

    public String getResponse() {
        return res;
    }

    public void setResponse(String res) {
        this.res = res;
    }
}
