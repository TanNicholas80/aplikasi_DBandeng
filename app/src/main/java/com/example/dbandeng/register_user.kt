package com.example.dbandeng

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dbandeng.modul.ModulUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class register_user : AppCompatActivity(), View.OnClickListener {
    lateinit var inputNama: EditText
    lateinit var inputEmail: EditText
    lateinit var inputPass: EditText
    lateinit var btnReg_user: Button
    var interfaceDbandeng: InterfaceDbandeng? = null
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
        if(view.id ==R.id.register) {
            val xNama = inputNama.text.toString()
            val xEmail = inputEmail.text.toString()
            val xPass = inputPass.text.toString()

            interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java);
            val login: Call<ModulUser>? = interfaceDbandeng?.registerUser(xNama,xEmail,xPass)
            login?.enqueue(object : Callback<ModulUser>{
                override fun onResponse(call: Call<ModulUser>, response: Response<ModulUser>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@register_user, "Berhasil Login user", Toast.LENGTH_LONG).show()
                        val layoutLogin = Intent(this@register_user, login_user::class.java)
                        startActivity(layoutLogin);
                    } else {

                        Toast.makeText(this@register_user, "Gagal Login", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<ModulUser>, t: Throwable) {
                    Log.d("RegisUser", t.message.toString());
                    Toast.makeText(this@register_user, "Gagal" + t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}