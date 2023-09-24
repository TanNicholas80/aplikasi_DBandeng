package com.example.dbandeng.modul;

import java.util.ArrayList;
import java.util.List;

public class ModulProdukNew {
    String beratProduk;
    String dskProduk;
    String foto_produk;
    String hrgProduk;
    String id;
    String link;
    String mitra_id;
    String nmProduk;
    String stok;
    public ModulProdukNew(String beratProduk, String dskProduk, String foto_produk, String hrgProduk, String id, String link, String mitra_is, String nmProduk, String stok, List<ModulProdukNew> produk) {
        this.beratProduk = beratProduk;
        this.dskProduk = dskProduk;
        this.foto_produk = foto_produk;
        this.hrgProduk = hrgProduk;
        this.id = id;
        this.link = link;
        this.mitra_id = mitra_is;
        this.nmProduk = nmProduk;
        this.stok = stok;
    }


    public String getBeratProduk() {
        return beratProduk;
    }

    public void setBeratProduk(String beratProduk) {
        this.beratProduk = beratProduk;
    }

    public String getDskProduk() {
        return dskProduk;
    }

    public void setDskProduk(String dskProduk) {
        this.dskProduk = dskProduk;
    }

    public String getFoto_produk() {
        return foto_produk;
    }

    public void setFoto_produk(String foto_produk) {
        this.foto_produk = foto_produk;
    }

    public String getHrgProduk() {
        return hrgProduk;
    }

    public void setHrgProduk(String htgProduk) {
        this.hrgProduk = htgProduk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMitra_id() {
        return mitra_id;
    }

    public void setMitra_id(String mitra_is) {
        this.mitra_id = mitra_is;
    }

    public String getNmProduk() {
        return nmProduk;
    }

    public void setNmProduk(String nmProduk) {
        this.nmProduk = nmProduk;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }
}
