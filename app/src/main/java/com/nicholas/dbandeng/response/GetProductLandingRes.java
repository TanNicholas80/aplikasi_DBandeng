package com.nicholas.dbandeng.response;

import com.nicholas.dbandeng.modul.ModulMitra;
import com.nicholas.dbandeng.modul.ModulProduk;

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
