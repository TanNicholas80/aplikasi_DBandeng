package com.example.dbandeng.response;

import com.google.gson.annotations.SerializedName;

public class deleteProdukRes {
    @SerializedName("data")
    String modulProdukNew;

    public deleteProdukRes(String modulProdukNew) {
        this.modulProdukNew = modulProdukNew;
    }

    public String getModulProdukNew() {
        return modulProdukNew;
    }

    public void setModulProdukNew(String modulProdukNew) {
        this.modulProdukNew = modulProdukNew;
    }
}
