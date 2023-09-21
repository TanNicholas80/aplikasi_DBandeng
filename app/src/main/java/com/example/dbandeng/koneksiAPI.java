package com.example.dbandeng;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class koneksiAPI {
    public static final String url_base="https://website-bandeng-backend2.vercel.app/api/api/";
    private static Retrofit retrofit;
    public static Retrofit Koneksi()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(url_base)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
