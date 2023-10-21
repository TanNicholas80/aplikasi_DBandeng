package com.nicholas.dbandeng.interfaceAPI;

import com.nicholas.dbandeng.response.LogoutUserRes;
import com.nicholas.dbandeng.response.ResponseNodeJs;

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
