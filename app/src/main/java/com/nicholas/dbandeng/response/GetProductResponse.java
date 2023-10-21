package com.nicholas.dbandeng.response;

import com.nicholas.dbandeng.modul.ModulMitra;

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
