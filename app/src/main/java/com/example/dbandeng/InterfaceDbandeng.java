package com.example.dbandeng;

import com.example.dbandeng.modul.ModulMitra;
import com.example.dbandeng.modul.ModulUser;
import com.example.dbandeng.response.*;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    @Headers("Accept: application/json")
    @GET("v1/mitra/read/{id}")
    Call<ProfilMitraResponse> getMitra(@Header("Authorization") String token, @Path("id") String id);

    // Edit Profile Mitra
    @FormUrlEncoded
    @POST("v1/mitra/edit/{id}")
    Call<EditProfilMitraRes> editMitra(@Header("Authorization") String token,
                                       @Path("id") String id,
                                       @Field("namaLengkap") String nama_lengkap,
                                       @Field("alamatMitra") String alamat,
                                       @Field("tglLahir") String tglLahir,
                                       @Field("jeniskel") String jkel,
                                       @Field("no_tlp") String no_hp);
    @Headers("Accept: application/json")
    @Multipart
    @POST("v1/mitra/edit-foto/{id}")
    Call<ProfilMitraResponse> editFotoMitra(@Header("Authorization") String token,
                                            @Path("id") String id,
                                            @Part MultipartBody.Part foto_mitra);

    @GET("v2/logout-mitra")
    Call<LogoutMitraRes> logoutMitra(@Header("Authorization") String token);

    @GET("product/read-mitra/{id}")
    Call<GetProductResponse> getProdukMitra(@Header("Authorization") String token, @Path("id") String id);
    @Multipart
    @FormUrlEncoded
    @POST("product/mitraId")
    Call<CreateProdukResponse> createProdukMitra(@Header("Authorization") String token,
                                                 @Path("id") String id,
                                                 @Field("nmProduk") String nmProduk,
                                                 @Part("foto_produk") MultipartBody.Part foto_produk,
                                                 @Field("hrgProduk") String hrgProduk,
                                                 @Field("stok") String stok,
                                                 @Field("beratProduk") String beratProduk,
                                                 @Field("dskProduk") String dskProduk,
                                                 @Field("link") String link);
}
