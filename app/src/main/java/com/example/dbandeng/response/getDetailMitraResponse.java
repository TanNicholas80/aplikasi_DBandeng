package com.example.dbandeng.response;

import com.example.dbandeng.modul.ModulMitra;
import com.example.dbandeng.modul.ModulMitraLP;
import com.example.dbandeng.modul.ModulProduk;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class getDetailMitraResponse {
    ArrayList<ModulMitraLP> data;

    public getDetailMitraResponse(ArrayList<ModulMitraLP> data) {
        this.data = data;
    }

    public ArrayList<ModulMitraLP> getData() {
        return data;
    }

    public void setData(ArrayList<ModulMitraLP> data) {
        this.data = data;
    }
}
