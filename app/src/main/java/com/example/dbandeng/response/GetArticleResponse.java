package com.example.dbandeng.response;

import com.example.dbandeng.modul.ModulMitra;
import com.example.dbandeng.modul.ModulNews;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetArticleResponse {
    List<ModulNews> data;

    public GetArticleResponse(List<ModulNews> data) {
        this.data = data;
    }

    public List<ModulNews> getData() {
        return data;
    }

    public void setData(List<ModulNews> data) {
        this.data = data;
    }
}
