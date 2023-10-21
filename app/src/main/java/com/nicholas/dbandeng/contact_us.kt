package com.nicholas.dbandeng

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class contact_us : AppCompatActivity() {
    private lateinit var ContactToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        // Setup Action Bar
        ContactToolbar = findViewById(R.id.contact_toolbar)
        setSupportActionBar(ContactToolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Beranda"
    }
}