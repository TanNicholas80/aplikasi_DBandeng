package com.example.dbandeng.modul;

public class ModulNews {
    String id_article, judul_article, isi_article, foto_news, tanggal_buat;
    public ModulNews(String id_article, String judul_article, String isi_article, String foto_news, String tanggal_buat) {
        this.id_article = id_article;
        this.judul_article = judul_article;
        this.isi_article = isi_article;
        this.foto_news = foto_news;
        this.tanggal_buat = tanggal_buat;
    }

    public String getId_article() {
        return id_article;
    }

    public void setId_article(String id_article) {
        this.id_article = id_article;
    }

    public String getJudul_article() {
        return judul_article;
    }

    public void setJudul_article(String judul_article) {
        this.judul_article = judul_article;
    }

    public String getIsi_article() {
        return isi_article;
    }

    public void setIsi_article(String isi_article) {
        this.isi_article = isi_article;
    }

    public String getFoto_news() {
        return foto_news;
    }

    public void setFoto_news(String foto_news) {
        this.foto_news = foto_news;
    }

    public String getTanggal_buat_article() {
        return tanggal_buat;
    }

    public void setTanggal_buat_article(String tanggal_buat) {
        this.tanggal_buat = tanggal_buat;
    }
}
