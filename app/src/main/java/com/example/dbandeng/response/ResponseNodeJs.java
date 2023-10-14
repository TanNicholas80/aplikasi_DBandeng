package com.example.dbandeng.response;

import com.example.dbandeng.modul.ModulLaporan;

import java.util.ArrayList;

public class ResponseNodeJs {
    String success;
    ArrayList<ModulLaporan> message;

    public ResponseNodeJs(String success, ArrayList<ModulLaporan> message) {
        this.success = success;
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<ModulLaporan> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<ModulLaporan> message) {
        this.message = message;
    }
}
