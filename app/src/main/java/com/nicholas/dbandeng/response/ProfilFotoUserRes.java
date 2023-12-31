package com.nicholas.dbandeng.response;

import com.nicholas.dbandeng.modul.ModulMitra;
import com.nicholas.dbandeng.modul.ModulUser;
import com.google.gson.annotations.SerializedName;

public class ProfilFotoUserRes {
    @SerializedName("data")
    ModulUser modulUser;
    @SerializedName("response")
    private String response;

    public ModulUser getModulUser() {
        return modulUser;
    }

    public void setModulUser(ModulUser modulUser) {
        this.modulUser = modulUser;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ProfilFotoUserRes(ModulUser modulUser) {
        this.modulUser = modulUser;
    }
}
