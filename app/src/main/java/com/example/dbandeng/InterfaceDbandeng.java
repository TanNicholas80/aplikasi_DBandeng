package com.example.dbandeng;

import com.example.dbandeng.modul.ModulMitra;
import com.example.dbandeng.modul.ModulUser;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceDbandeng {
    // Login Mitra
    @FormUrlEncoded
    @POST("api/v2/login")
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
}
