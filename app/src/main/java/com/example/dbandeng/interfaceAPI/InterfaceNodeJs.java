package com.example.dbandeng.interfaceAPI;

import com.example.dbandeng.response.LogoutUserRes;
import com.example.dbandeng.response.ResponseNodeJs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface InterfaceNodeJs {
    @GET("laporan/available")
    Call<ResponseNodeJs> getAvailableLaporan(@Query("id") String id);

    @GET("laporan")
    Call<okhttp3.ResponseBody> downloaadFilePDF(@Query("id") String id, @Query("date") String date);
}
