package com.example.dbandeng;

import com.example.dbandeng.modul.ModulMitra;
import com.example.dbandeng.modul.ModulMitraLP;
import com.example.dbandeng.modul.ModulResponse;
import com.example.dbandeng.modul.ModulUser;
import com.example.dbandeng.response.*;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    // Get User Profile
    @Headers("Accept: application/json")
    @GET("get/user/{id}")
    Call<ProfilUserResponse> getUser(@Header("Authorization") String token, @Path("id") String id);

    // Edit Profile User
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("edit/user/{id}")
    Call<EditProfilUserRes> editUser (@Header("Authorization") String token,
                              @Path("id") String id,
                              @Field("name") String name,
                              @Field("email") String email,
                              @Field("no_user") String no_user,
                              @Field("alamatUser") String alamatUser);

    // Edit FOTO profil User
    @Headers("Accept: application/json")
    @Multipart
    @POST("edit-foto/user/{id}")
    Call<ProfilFotoUserRes> editFotoUser (@Header("Authorization") String token,
                                          @Path("id") String id,
                                          @Part MultipartBody.Part foto_user);
    // logout User
    @GET("logout-user")
    Call<LogoutUserRes> logoutUser(@Header("Authorization") String token);

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
    @Headers("Accept: application/json")
    @Multipart
    @POST("product/{id}")
    Call<CreateProdukResponse> createProdukMitra(@Header("Authorization") String token,
                                                 @Path("id") String id,
                                                 @Part("nmProduk") RequestBody nmProduk,
                                                 @Part MultipartBody.Part foto_produk,
                                                 @Part("hrgProduk") RequestBody hrgProduk,
                                                 @Part("stok") RequestBody stok,
                                                 @Part("beratProduk") RequestBody beratProduk,
                                                 @Part("dskProduk") RequestBody dskProduk,
                                                 @Part("link") RequestBody link);
    @Headers("Accept: application/json")
    @Multipart
    @POST("product/edit/{id}")
    Call<updateProdukResponse> updateProdukMitra(@Header("Authorization") String token,
                             @Path("id") String id,
                             @Part("nmProduk") RequestBody nmProduk,
                             @Part MultipartBody.Part foto_produk,
                             @Part("hrgProduk") RequestBody hrgProduk,
                             @Part("stok") RequestBody stok,
                             @Part("beratProduk") RequestBody beratProduk,
                             @Part("dskProduk") RequestBody dskProduk,
                             @Part("link") RequestBody link);

    @Headers("Accept: application/json")
    @Multipart
    @POST("product/edit/{id}")
    Call<updateProdukResponse> updateProdukMitraWithoutPhoto(@Header("Authorization") String token,
                                                 @Path("id") String id,
                                                 @Part("nmProduk") RequestBody nmProduk,
                                                 @Part("hrgProduk") RequestBody hrgProduk,
                                                 @Part("stok") RequestBody stok,
                                                 @Part("beratProduk") RequestBody beratProduk,
                                                 @Part("dskProduk") RequestBody dskProduk,
                                                 @Part("link") RequestBody link);
    @Headers("Accept: application/json")
    @DELETE("product/delete/{id}")
    Call<deleteProdukRes> deleteProduk(@Header("Authorization") String token,
                        @Path("id") String id);


    @Headers("Accept: application/json")
    @GET("article/read-all")
    Call<GetArticleResponse> GetArticle();

    @Headers("Accept: application/json")
    @GET("mitra/all")
    Call<GetAllMitraLandingResponse> getAllMitra();

    @Headers("Accept: application/json")
    @GET("product/home-aplikasi")
    Call<GetProductLandingRes> GetAllProduk();

    @Headers("Accept: application/json")
    @GET("mitra/all/product/{id}")
    Call<getDetailMitraResponse> getDetailMitra(@Path("id") String id);

    @Headers("Accept: application/json")
    @GET("product/read/{id}")
    Call<getProductByMitraRes> getProductByMitra(@Path("id") String id);
}
