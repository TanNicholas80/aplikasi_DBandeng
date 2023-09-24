package com.example.dbandeng.response;

import com.example.dbandeng.modul.ModulMitra;

public class GetProductResponse {
    String data;
    public GetProductResponse(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
