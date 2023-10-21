package com.nicholas.dbandeng.modul;

import com.google.gson.annotations.SerializedName;

public class ModulMitraLP {
    String id;
    String namaMitra;
    String foto_mitra;
    String no_tlp;
    String alamatMitra;


    public ModulMitraLP(String id, String namaMitra, String foto_mitra, String no_tlp, String alamatMitra) {
        this.id = id;
        this.namaMitra = namaMitra;
        this.foto_mitra = foto_mitra;
        this.no_tlp = no_tlp;
        this.alamatMitra = alamatMitra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaMitra() {
        return namaMitra;
    }

    public void setNamaMitra(String namaMitra) {
        this.namaMitra = namaMitra;
    }

    public String getFoto_mitra() {
        return foto_mitra;
    }

    public void setFoto_mitra(String foto_mitra) {
        this.foto_mitra = foto_mitra;
    }

    public String getNo_hp() {
        return no_tlp;
    }

    public void setNo_hp(String no_tlp) {
        this.no_tlp = no_tlp;
    }

    public String getAlamat() {
        return alamatMitra;
    }

    public void setAlamat(String alamatMitra) {
        this.alamatMitra = alamatMitra;
    }
}


