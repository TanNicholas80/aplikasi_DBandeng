package com.example.dbandeng.response;

import com.example.dbandeng.modul.ModulMitra;
import com.example.dbandeng.modul.ModulProduk;

import java.util.List;

public class GetProductLandingRes {
    List<ModulProduk> data;

    public GetProductLandingRes(List<ModulProduk> data) {
        this.data = data;
    }

    public List<ModulProduk> getData() {
        return data;
    }

    public void setData(List<ModulProduk> data) {
        this.data = data;
    }

}
