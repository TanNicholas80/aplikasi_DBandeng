package com.nicholas.dbandeng.modul;

public class ModulProduk {
    String id, id_Mitra, nmProduk, namaMitra, dskProduk, foto_produk, stok, hrgProduk, created_at, beratProduk, link;
    public ModulProduk(String id, String id_Mitra, String nmProduk, String namaMitra, String dskProduk, String foto_produk, String stok, String hrgProduk, String beratProduk, String link, String created_at) {
        this.id = id;
        this.id_Mitra = id_Mitra;
        this.namaMitra = namaMitra;
        this.foto_produk = foto_produk;
        this.nmProduk = nmProduk;
        this.dskProduk = dskProduk;
        this.stok = stok;
        this.beratProduk = beratProduk;
        this.link = link;
        this.hrgProduk = hrgProduk;
        this.created_at = created_at;
    }

    public String getId_produk() {
        return id;
    }

    public void setId_produk(String id) {
        this.id = id;
    }

    public String getId_Mitra() {
        return id_Mitra;
    }

    public void setId_Mitra(String id_Mitra) {
        this.id_Mitra = id_Mitra;
    }

    public String getNama_produk() {
        return nmProduk;
    }

    public void setNama_produk(String nmProduk) {
        this.nmProduk = nmProduk;
    }

    public String getNama_mitra() {
        return namaMitra;
    }

    public void setNama_mitra(String namaMitra) {
        this.namaMitra = namaMitra;
    }

    public String getFoto_produk() {
        return foto_produk;
    }

    public void setFoto_produk(String foto_produk) {
        this.foto_produk = foto_produk;
    }

    public String getKeterangan_produk() {
        return dskProduk;
    }

    public void setKeterangan_produk(String dskProduk) {
        this.dskProduk = dskProduk;
    }

    public String getfoto_produk() {
        return foto_produk;
    }

    public void setfoto_produk(String foto_produk) {
        this.foto_produk = foto_produk;
    }

    public String getStok_produk() {
        return stok;
    }

    public void setStok_produk(String stok) {
        this.stok = stok;
    }

    public String getBerat_produk() {
        return beratProduk;
    }

    public void setBerat_produk(String beratProduk) {
        this.beratProduk = beratProduk;
    }

    public String getLink_produk() {
        return link;
    }

    public void setLink_produk(String link) {
        this.link = link;
    }

    public String getHarga_produk() {
        return hrgProduk;
    }

    public void setHarga_produk(String hrgProduk) {
        this.hrgProduk = hrgProduk;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
