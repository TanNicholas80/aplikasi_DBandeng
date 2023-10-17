package com.example.dbandeng

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dx.dxloadingbutton.lib.LoadingButton
import com.example.dbandeng.modul.ModulUser
import com.example.dbandeng.response.LoginRequest
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class login_user : AppCompatActivity(), View.OnClickListener {
    lateinit var inputEmail: TextInputLayout
    lateinit var inputPass: TextInputLayout
    lateinit var btnLogin_user: LoadingButton
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

        if(view.id==R.id.btnLogin) {
            btnLogin_user.startLoading()
            val xEmail = inputEmail.editText!!.text.toString()
            val xPass = inputPass.editText!!.text.toString()
            interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)

            val login: Call<ModulUser>? = interfaceDbandeng?.loginUser(xEmail,xPass)
            login?.enqueue(object : Callback<ModulUser> {
                override fun onResponse(call: Call<ModulUser>, response: Response<ModulUser>) {
                    if (response.isSuccessful) {
                        val modulUser: ModulUser? = response.body()
                        val AuthToken = modulUser?.getToken()
                        val rep = modulUser?.getResponse()

                        val loginRequest = LoginRequest()
                        loginRequest.token = AuthToken
                        loginRequest.id = modulUser?.id_User
                        loginRequest.email = modulUser?.email
                        loginRequest.response = rep

                        val preferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
                        val editor = preferences.edit()
                        editor.putString("auth_token", AuthToken)
                        editor.putString("id_user", modulUser?.id_User)
                        editor.putString("nama_user", modulUser?.name)
                        editor.putString("foto_user", modulUser?.foto_user)
                        editor.apply()

                        val textToaster = rep
                        print(textToaster)
                        print(AuthToken)
                        btnLogin_user.loadingSuccessful()
                        Toast.makeText(this@login_user, "${textToaster}", Toast.LENGTH_LONG).show()
                        val landingPageUser = Intent(this@login_user, meow_button_parent::class.java)
                        startActivity(landingPageUser);
                    } else {
                        btnLogin_user.loadingFailed()
                        Toast.makeText(this@login_user, "Gagal Login", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<ModulUser>, t: Throwable) {
                    Log.d("RegisUser", t.message.toString());
                    btnLogin_user.loadingFailed()
                    Toast.makeText(this@login_user, "Gagal" + t.message, Toast.LENGTH_SHORT).show()
                }
            })

        }
    }
}