package com.example.dbandeng

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class hasil_iot : AppCompatActivity() {
    private lateinit var hasilToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_iot)
        // setup action bar
        hasilToolbar = findViewById(R.id.hasil_iot_toolbar)
        setSupportActionBar(hasilToolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Beranda"
    }
}