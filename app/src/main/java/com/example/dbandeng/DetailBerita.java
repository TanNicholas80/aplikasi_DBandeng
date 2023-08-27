package com.example.dbandeng;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

public class DetailBerita extends AppCompatActivity {
    Toolbar tb_detail_berita;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        // setup Action Bar
        tb_detail_berita = findViewById(R.id.toolbar_detail_berita);
        setSupportActionBar(tb_detail_berita);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Berita");
    }
}