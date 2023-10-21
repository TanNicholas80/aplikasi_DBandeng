package com.nicholas.dbandeng

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dx.dxloadingbutton.lib.LoadingButton
import com.nicholas.dbandeng.modul.ModulMitra
import com.nicholas.dbandeng.response.LoginRequest
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class login_admin : AppCompatActivity(), View.OnClickListener {
    lateinit var inputEmail: TextInputLayout
    lateinit var inputPass: TextInputLayout
    lateinit var btnLogin_admin: LoadingButton
    var interfaceDbandeng: InterfaceDbandeng? = null
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
            // Login Admin
            btnLogin_admin.startLoading()
            interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
            val xEmail = inputEmail.editText!!.text.toString()
            val xPass = inputPass.editText!!.text.toString()
            interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
            val login: Call<ModulMitra>? = interfaceDbandeng?.loginMitra(xEmail, xPass)
            login?.enqueue(object : Callback<ModulMitra> {
                override fun onResponse(call: Call<ModulMitra>, response: Response<ModulMitra>) {
                    Log.d("loginAdmin", response.code().toString());
                    if (response.isSuccessful) {
                        val modulMitra: ModulMitra? = response.body()
                        val AuthToken = modulMitra?.getToken()
                        val rep = modulMitra?.getResponse()

                        val loginRequest = LoginRequest()
                        loginRequest.token = AuthToken
                        loginRequest.id = modulMitra?.id_Mitra
                        loginRequest.email = modulMitra?.email
                        loginRequest.response = rep

                        val preferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
                        val editor = preferences.edit()
                        editor.putString("auth_token", AuthToken)
                        editor.putString("id_mitra", modulMitra?.id_Mitra)
                        editor.apply()
                        val textToaster = rep
                        print(textToaster)
                        print(AuthToken)
                        btnLogin_admin.loadingSuccessful()
                        Toast.makeText(this@login_admin, "${textToaster}", Toast.LENGTH_LONG).show()
                        val loginAdmin_layout = Intent(this@login_admin, Beranda::class.java);// ntar ganti beranda lagi

                        startActivity(loginAdmin_layout);
                    } else {
                        btnLogin_admin.loadingFailed()
                        Toast.makeText(this@login_admin, "Gagal Login", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ModulMitra>, t: Throwable) {
                    btnLogin_admin.loadingFailed()
                    Log.d("loginAdmin", t.message.toString());
                    Toast.makeText(this@login_admin, "Gagal" + t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}