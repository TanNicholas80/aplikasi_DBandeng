package com.example.dbandeng.modul;

public class ModulProduk {
    String id_produk, nama_produk, keterangan_produk, stok_produk, harga_produk;
    public ModulProduk(String id_produk, String nama_produk, String keterangan_produk, String stok_produk, String harga_produk) {
        this.id_produk = id_produk;
        this.nama_produk = nama_produk;
        this.keterangan_produk = keterangan_produk;
        this.stok_produk = stok_produk;
        this.harga_produk = harga_produk;
    }

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getKeterangan_produk() {
        return keterangan_produk;
    }

    public void setKeterangan_produk(String keterangan_produk) {
        this.keterangan_produk = keterangan_produk;
    }

    public String getStok_produk() {
        return stok_produk;
    }

    public void setStok_produk(String stok_produk) {
        this.stok_produk = stok_produk;
    }

    public String getHarga_produk() {
        return harga_produk;
    }

    public void setHarga_produk(String harga_produk) {
        this.harga_produk = harga_produk;
    }


}
