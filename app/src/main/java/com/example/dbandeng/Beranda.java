package com.example.dbandeng;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Beranda extends AppCompatActivity implements View.OnClickListener {
    LinearLayout btnProfil, btnKelolaIot, btnHasilIot, btnCRUDProduk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        btnCRUDProduk = findViewById(R.id.btnCrudProduk);
        btnKelolaIot = findViewById(R.id.btnKelolaIot);
        btnHasilIot = findViewById(R.id.btnHasilIot);
        btnProfil = findViewById(R.id.btnProfil);

        btnCRUDProduk.setOnClickListener(this);
        btnKelolaIot.setOnClickListener(this);
        btnHasilIot.setOnClickListener(this);
        btnProfil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnCrudProduk) {
            startActivity(new Intent(Beranda.this, CRUD_Product.class));
        } else if (v.getId()==R.id.btnKelolaIot) {
            startActivity(new Intent(Beranda.this, kelola_iot.class));
        } else if (v.getId()==R.id.btnHasilIot) {
            startActivity(new Intent(Beranda.this, hasil_iot.class));
        } else {
            startActivity(new Intent(Beranda.this, landing_page_profile.class));
        }
    }
}