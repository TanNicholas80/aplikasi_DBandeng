package com.nicholas.dbandeng.response;

import com.nicholas.dbandeng.modul.ModulMitraLP;

import java.util.ArrayList;

public class GetAllMitraLandingResponse {
    ArrayList<ModulMitraLP> data;

    public GetAllMitraLandingResponse(ArrayList<ModulMitraLP> data) {
        this.data = data;
    }

    public ArrayList<ModulMitraLP> getData() {
        return data;
    }

    public void setData(ArrayList<ModulMitraLP> data) {
        this.data = data;
    }
}


