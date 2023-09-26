package com.example.dbandeng.response;

import com.example.dbandeng.modul.ModulProdukNew;
import com.google.gson.annotations.SerializedName;

public class CreateProdukResponse {
    @SerializedName("data")
    String modulProdukNew;
    @SerializedName("response")
    String response;

    public CreateProdukResponse(String modulProdukNew, String response) {
        this.modulProdukNew = modulProdukNew;
        this.response = response;
    }

    public String getModulProdukNew() {
        return modulProdukNew;
    }

    public void setModulProdukNew(String modulProdukNew) {
        this.modulProdukNew = modulProdukNew;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
