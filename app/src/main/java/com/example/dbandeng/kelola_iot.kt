package com.example.dbandeng

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class kelola_iot : AppCompatActivity() {
    private lateinit var kelolaToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kelola_iot)
        // setup action bar
        kelolaToolbar = findViewById(R.id.kelola_iot_toolbar)
        setSupportActionBar(kelolaToolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Beranda"
    }
}