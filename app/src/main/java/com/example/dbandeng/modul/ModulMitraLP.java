package com.example.dbandeng.modul;

public class ModulMitraLP {
    String id;
    String namaMitra;
    String foto_mitra;


    public ModulMitraLP(String id, String namaMitra, String foto_mitra) {
        this.id = id;
        this.namaMitra = namaMitra;
        this.foto_mitra = foto_mitra;
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
}


