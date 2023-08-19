package com.example.dbandeng

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import android.widget.EditText

class login_admin : AppCompatActivity(), View.OnClickListener {
    lateinit var inputEmail: EditText
    lateinit var inputPass: EditText
    lateinit var btnLogin_admin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_admin)
        inputEmail = findViewById(R.id.editEmail);
        inputPass = findViewById(R.id.editPass);
        btnLogin_admin = findViewById(R.id.btnLogin);

        btnLogin_admin.setOnClickListener(this)
    }
   override fun onClick(view: View) {
        if(view.id==R.id.btnLogin) {
            val xEmail = inputEmail.text.toString()
            val xPass = inputPass.text.toString()
        }
    }
}