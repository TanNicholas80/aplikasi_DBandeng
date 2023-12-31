package com.nicholas.dbandeng.modul;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class Token {
    public String token;

    public Token(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
public class ModulMitra {
   @SerializedName("id")
    String id_Mitra;
    @SerializedName("namaLengkap")
    String   nama_lengkap;
    @SerializedName("namaMitra")
    String    nama_mitra;
    @SerializedName("foto_mitra")
    String    foto_mitra;
    @SerializedName("jeniskel")
    String    jkel;
    @SerializedName("no_tlp")
    String    no_hp;
    @SerializedName("alamatMitra")
    String   alamat;
    String   email;
    String   password;
    String tglLahir;

    List<ModulProdukNew> products;
    List<ModulNews> news;

    public Token token;
    private String response;


    public ModulMitra(String id_Mitra, String nama_lengkap, String nama_mitra, String foto_mitra, String jkel, String no_hp, String alamat, String email, String password, List<ModulProdukNew> products, List<ModulNews> news) {
        this.id_Mitra = id_Mitra;
        this.nama_lengkap = nama_lengkap;
        this.nama_mitra = nama_mitra;
        this.foto_mitra = foto_mitra;
        this.jkel = jkel;
        this.no_hp = no_hp;
        this.alamat = alamat;
        this.email = email;
        this.password = password;
        this.products = products;
        this.news = news;
    }

    public List<ModulProdukNew> getProducts() {
        return products;
    }

    public void setProducts(List<ModulProdukNew> products) {
        this.products = products;
    }

    public List<ModulNews> getNews() {
        return news;
    }

    public void setNews(List<ModulNews> news) {
        this.news = news;
    }

    public String getId_Mitra() {
        return id_Mitra;
    }

    public void setId_Mitra(String id_Mitra) {
        this.id_Mitra = id_Mitra;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNama_mitra() {
        return nama_mitra;
    }

    public void setNama_mitra(String nama_mitra) {
        this.nama_mitra = nama_mitra;
    }

    public String getFoto_mitra() {
        return foto_mitra;
    }

    public void setFoto_mitra(String foto_mitra) {
        this.foto_mitra = foto_mitra;
    }

    public String getJkel() {
        return jkel;
    }

    public void setJkel(String jkel) {
        this.jkel = jkel;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() { return token.getToken(); }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getResponse() { return response; }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }
}
