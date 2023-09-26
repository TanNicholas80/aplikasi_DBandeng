package com.example.dbandeng;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class koneksiAPI {
    public static final String url_base="https://website-bandeng-backend2.vercel.app/api/api/";
    private static Retrofit retrofit;
    public static Retrofit Koneksi()
    {
        if(retrofit==null)
        {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();

            retrofit=new Retrofit.Builder()
                    .baseUrl(url_base)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
