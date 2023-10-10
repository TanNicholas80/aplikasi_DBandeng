package com.example.dbandeng.response;

import com.example.dbandeng.modul.ModulNews;

import java.util.List;

public class GetSpesifikNews {
    List<ModulNews> response;

    public GetSpesifikNews(List<ModulNews> response) {
        this.response = response;
    }

    public List<ModulNews> getData() {
        return response;
    }

    public void setData(List<ModulNews> response) {
        this.response = response;
    }
}
