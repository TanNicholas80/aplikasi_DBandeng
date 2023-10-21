package com.nicholas.dbandeng.response;

import com.nicholas.dbandeng.modul.ModulNews;

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
