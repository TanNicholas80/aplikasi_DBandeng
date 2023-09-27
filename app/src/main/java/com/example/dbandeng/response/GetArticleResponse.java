package com.example.dbandeng.response;

import com.example.dbandeng.modul.ModulMitra;
import com.example.dbandeng.modul.ModulNews;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetArticleResponse {
    ArrayList<ModulNews> data;
    public GetArticleResponse(ArrayList<ModulNews> data) {
        this.data = data;
    }

    public ArrayList<ModulNews> getData() {
        return data;
    }

    public void setData(ArrayList<ModulNews> data) {
        this.data = data;
    }
}
