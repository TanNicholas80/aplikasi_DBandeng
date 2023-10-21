package com.nicholas.dbandeng.response;

import com.nicholas.dbandeng.modul.ModulMitra;
import com.google.gson.annotations.SerializedName;

public class ProfilMitraResponse {
    @SerializedName("data")
    ModulMitra modulMitra;
    @SerializedName("response")
    private String response;

    public ModulMitra getModulMitra() {
        return modulMitra;
    }

    public void setModulMitra(ModulMitra modulMitra) {
        this.modulMitra = modulMitra;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ProfilMitraResponse(ModulMitra modulMitra) {
        this.modulMitra = modulMitra;
    }
}
