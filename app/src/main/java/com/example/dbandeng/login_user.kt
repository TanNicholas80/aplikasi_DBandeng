package com.example.dbandeng

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dbandeng.modul.ModulUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class login_user : AppCompatActivity(), View.OnClickListener {
    lateinit var inputEmail: EditText
    lateinit var inputPass: EditText
    lateinit var btnLogin_user: Button
    var interfaceDbandeng: InterfaceDbandeng? = null
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

            interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)

            val login: Call<ModulUser>? = interfaceDbandeng?.loginUser(xEmail,xPass)
            login?.enqueue(object : Callback<ModulUser> {
                override fun onResponse(call: Call<ModulUser>, response: Response<ModulUser>) {
                    if (response.isSuccessful) {

                        Toast.makeText(this@login_user, "Berhasil Login user", Toast.LENGTH_LONG).show()
                        val landingPageUser = Intent(this@login_user, lading_page_home::class.java)
                        startActivity(landingPageUser);
                    } else {

                        Toast.makeText(this@login_user, "Gagal Login", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<ModulUser>, t: Throwable) {
                    Log.d("RegisUser", t.message.toString());
                    Toast.makeText(this@login_user, "Gagal" + t.message, Toast.LENGTH_SHORT).show()
                }
            })

        }
    }
}