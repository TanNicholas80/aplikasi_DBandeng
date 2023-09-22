package com.example.dbandeng.response;

import com.example.dbandeng.modul.ModulMitra;
import com.google.gson.annotations.SerializedName;

public class ProfilMitraResponse {
    @SerializedName("data")
    ModulMitra modulMitra;

    public ModulMitra getModulMitra() {
        return modulMitra;
    }

    public void setModulMitra(ModulMitra modulMitra) {
        this.modulMitra = modulMitra;
    }

    public ProfilMitraResponse(ModulMitra modulMitra) {
        this.modulMitra = modulMitra;
    }
}
