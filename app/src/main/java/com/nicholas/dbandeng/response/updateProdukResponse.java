package com.nicholas.dbandeng.response;

import com.google.gson.annotations.SerializedName;

public class updateProdukResponse {
    @SerializedName("data")
    String modulProdukNew;

    public updateProdukResponse(String modulProdukNew) {
        this.modulProdukNew = modulProdukNew;
    }

    public String getModulProdukNew() {
        return modulProdukNew;
    }

    public void setModulProdukNew(String modulProdukNew) {
        this.modulProdukNew = modulProdukNew;
    }
}
