package com.example.dbandeng.response;

import com.example.dbandeng.modul.ModulProduk;

import java.util.ArrayList;
import java.util.List;

public class getProductByMitraRes {
    ArrayList<ModulProduk> data;

    public getProductByMitraRes(ArrayList<ModulProduk> data) {
        this.data = data;
    }

    public ArrayList<ModulProduk> getData() {
        return data;
    }

    public void setData(ArrayList<ModulProduk> data) {
        this.data = data;
    }
}
