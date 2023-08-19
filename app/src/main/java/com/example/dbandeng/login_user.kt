package com.example.dbandeng

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class login_user : AppCompatActivity(), View.OnClickListener {
    lateinit var inputEmail: EditText
    lateinit var inputPass: EditText
    lateinit var btnLogin_user: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user)
        inputEmail = findViewById(R.id.emailUser);
        inputPass = findViewById(R.id.passUser);
        btnLogin_user = findViewById(R.id.btnLogin);

        btnLogin_user.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id ==R.id.btnLogin) {
            val xEmail = inputEmail.text.toString()
            val xPass = inputPass.text.toString()
        }
    }
}