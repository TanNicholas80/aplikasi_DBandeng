package com.example.dbandeng.response;

import com.example.dbandeng.modul.ModulProdukNew;
import com.google.gson.annotations.SerializedName;

public class CreateProdukResponse {
    @SerializedName("data")
    ModulProdukNew modulProdukNew;
    @SerializedName("response")
    private String response;

    public ModulProdukNew getModulProdukNew() {
        return modulProdukNew;
    }

    public void setModulProdukNew(ModulProdukNew modulProdukNew) {
        this.modulProdukNew = modulProdukNew;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public CreateProdukResponse(ModulProdukNew modulProdukNew) {
        this.modulProdukNew = modulProdukNew;
    }
}
