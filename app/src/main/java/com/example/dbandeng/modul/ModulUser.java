package com.example.dbandeng.modul;

public class ModulUser {
    String id_User, name, alamatUser, no_user, foto_user, email, password;

    public ModulUser(String id_User, String name, String alamatUser, String no_user, String foto_user, String email, String password) {
        this.id_User = id_User;
        this.name = name;
        this.alamatUser = alamatUser;
        this.no_user = no_user;
        this.foto_user = foto_user;
        this.email = email;
        this.password = password;
    }

    public String getId_User() {
        return id_User;
    }

    public void setId_User(String id_User) {
        this.id_User = id_User;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamatUser() {
        return alamatUser;
    }

    public void setAlamatUser(String alamatUser) {
        this.alamatUser = alamatUser;
    }

    public String getNo_user() {
        return no_user;
    }

    public void setNo_user(String no_user) {
        this.no_user = no_user;
    }

    public String getFoto_user() {
        return foto_user;
    }

    public void setFoto_user(String foto_user) {
        this.foto_user = foto_user;
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
}
