package com.nicholas.dbandeng.modul;

public class ModulLogIOT {
    String tanggal, fullTanggal;
    float hours_work;

    public ModulLogIOT(String tanggal, String fullTanggal, float hours_work) {
        this.tanggal = tanggal;
        this.fullTanggal = fullTanggal;
        this.hours_work = hours_work;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getFullTanggal() {
        return fullTanggal;
    }

    public void setFullTanggal(String fullTanggal) {
        this.fullTanggal = fullTanggal;
    }

    public float getHours_work() {
        return hours_work;
    }

    public void setHours_work(float hours_work) {
        this.hours_work = hours_work;
    }
}
