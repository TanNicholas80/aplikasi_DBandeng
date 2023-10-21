package com.nicholas.dbandeng.response;

import com.nicholas.dbandeng.modul.ModulUser;
import com.google.gson.annotations.SerializedName;

public class ProfilUserResponse {
    @SerializedName("data")
    ModulUser modulUser;
    @SerializedName("response")
    private String response;

    public ModulUser getModulUser() {
        return modulUser;
    }

    public void setModulMitra(ModulUser modulUser) {
        this.modulUser = modulUser;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ProfilUserResponse(ModulUser modulUser) {
        this.modulUser = modulUser;
    }
}
