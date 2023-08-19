package com.example.dbandeng

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class register_user : AppCompatActivity(), View.OnClickListener {
    lateinit var inputNama: EditText
    lateinit var inputEmail: EditText
    lateinit var inputPass: EditText
    lateinit var btnReg_user: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        inputNama = findViewById(R.id.regInputNama);
        inputEmail = findViewById(R.id.regInputEmail);
        inputPass = findViewById(R.id.regInputPass);
        btnReg_user = findViewById(R.id.register);

        btnReg_user.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id ==R.id.btnLogin) {
            val xNama = inputNama.text.toString()
            val xEmail = inputEmail.text.toString()
            val xPass = inputPass.text.toString()
        }
    }
}