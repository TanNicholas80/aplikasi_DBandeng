package com.nicholas.dbandeng

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class choose_register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_register)
        val btnUser = findViewById(R.id.regUser) as Button;
        val btnAdmin = findViewById(R.id.logAdmin) as Button;
        btnUser.setOnClickListener {
            val register_user_layout = Intent(this@choose_register, login_user::class.java);

            startActivity(register_user_layout);
        }
        btnAdmin.setOnClickListener {
            val login_admin_layout = Intent(this@choose_register, login_admin::class.java);

            startActivity(login_admin_layout);
        }
    }
}