package com.nicholas.dbandeng.modul;

import java.util.ArrayList;

public class ModulResponse {
    ArrayList<ModulMitraLP> listMitra;

    public ModulResponse(ArrayList<ModulMitraLP> listMitra) {
        this.listMitra = listMitra;
    }

    public ArrayList<ModulMitraLP> getListMitra() {
        return listMitra;
    }

    public void setListMitra(ArrayList<ModulMitraLP> listMitra) {
        this.listMitra = listMitra;
    }
}
