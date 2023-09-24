package com.example.dbandeng;

import com.example.dbandeng.modul.ModulMitra;
import com.example.dbandeng.modul.ModulUser;
import com.example.dbandeng.response.LoginRequest;
import com.example.dbandeng.response.ProfilMitraResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceDbandeng {
    // Login Mitra
    @FormUrlEncoded
    @POST("v2/login")
    Call<ModulMitra> loginMitra (@Field("email") String email,
                                 @Field("password") String password);
    // Register User
    @FormUrlEncoded
    @POST("register/user")
    Call<ModulUser> registerUser (@Field("name") String name,
                                  @Field("email") String email,
                                  @Field("password") String password);
    // Login User
    @FormUrlEncoded
    @POST("login/user")
    Call<ModulUser> loginUser (@Field("email") String email,
                               @Field("password") String password);
    // Edit Profile User
    @FormUrlEncoded
    @POST("edit/user/{id}")
    Call<ModulUser> editUser (@Field("name") String name,
                              @Field("email") String email,
                              @Field("no_user") String no_user,
                              @Field("alamatUser") String alamatUser);

    //Get Mitra Profile
    @GET("v1/mitra/read/{id}")
    Call<ProfilMitraResponse> getMitra(@Header("Authorization") String token, @Path("id") String id);

    // Edit Profile Mitra
    @FormUrlEncoded
    @POST("v1/mitra/edit/{id}")
    Call<ProfilMitraResponse> editMitra(@Header("Authorization") String token, @Path("id") String id);

    @FormUrlEncoded
    @POST("v1/mitra/edit-foto/{id}")
    Call<ProfilMitraResponse> editFotoMitra(@Header("Authorization") String token, @Path("id") String id);
}
