package com.nicholas.dbandeng.modul;

public class ModulLaporan {
    String tanggal, tanggalRaw;
    int besar_count, sedang_count, kecil_count, total_berat, total_harga;

    public ModulLaporan(String tanggal, String tanggalRaw, int besar_count, int sedang_count, int kecil_count, int total_berat, int total_harga) {
        this.tanggal = tanggal;
        this.tanggalRaw = tanggalRaw;
        this.besar_count = besar_count;
        this.sedang_count = sedang_count;
        this.kecil_count = kecil_count;
        this.total_berat = total_berat;
        this.total_harga = total_harga;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTanggalRaw() {
        return tanggalRaw;
    }

    public void setTanggalRaw(String tanggalRaw) {
        this.tanggalRaw = tanggalRaw;
    }

    public int getBesar_count() {
        return besar_count;
    }

    public void setBesar_count(int besar_count) {
        this.besar_count = besar_count;
    }

    public int getSedang_count() {
        return sedang_count;
    }

    public void setSedang_count(int sedang_count) {
        this.sedang_count = sedang_count;
    }

    public int getKecil_count() {
        return kecil_count;
    }

    public void setKecil_count(int kecil_count) {
        this.kecil_count = kecil_count;
    }

    public int getTotal_berat() {
        return total_berat;
    }

    public void setTotal_berat(int total_berat) {
        this.total_berat = total_berat;
    }

    public int getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }
}
