package com.example.dbandeng.modul;

public class ModulNews {
    String id,jdlArticle,isiArticle,foto_article,created_at,updated_at;

    public ModulNews(String id, String jdlArticle, String isiArticle, String foto_article, String created_at, String updated_at) {
        this.id = id;
        this.jdlArticle = jdlArticle;
        this.isiArticle = isiArticle;
        this.foto_article = foto_article;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJdlArticle() {
        return jdlArticle;
    }

    public void setJdlArticle(String jdlArticle) {
        this.jdlArticle = jdlArticle;
    }

    public String getIsiArticle() {
        return isiArticle;
    }

    public void setIsiArticle(String isiArticle) {
        this.isiArticle = isiArticle;
    }

    public String getFoto_article() {
        return foto_article;
    }

    public void setFoto_article(String foto_article) {
        this.foto_article = foto_article;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
