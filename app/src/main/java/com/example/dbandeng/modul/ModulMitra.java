package com.example.dbandeng.modul;

public class ModulMitra {
    String id_Mitra, nama_lengkap, nama_mitra, foto_mitra, jkel, no_hp, alamat, email, password;

    public ModulMitra(String id_Mitra, String nama_lengkap, String nama_mitra, String foto_mitra, String jkel, String no_hp, String alamat, String email, String password) {
        this.id_Mitra = id_Mitra;
        this.nama_lengkap = nama_lengkap;
        this.nama_mitra = nama_mitra;
        this.foto_mitra = foto_mitra;
        this.jkel = jkel;
        this.no_hp = no_hp;
        this.alamat = alamat;
        this.email = email;
        this.password = password;
    }

    public String getId_Mitra() {
        return id_Mitra;
    }

    public void setId_Mitra(String id_Mitra) {
        this.id_Mitra = id_Mitra;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNama_mitra() {
        return nama_mitra;
    }

    public void setNama_mitra(String nama_mitra) {
        this.nama_mitra = nama_mitra;
    }

    public String getFoto_mitra() {
        return foto_mitra;
    }

    public void setFoto_mitra(String foto_mitra) {
        this.foto_mitra = foto_mitra;
    }

    public String getJkel() {
        return jkel;
    }

    public void setJkel(String jkel) {
        this.jkel = jkel;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
