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
import com.example.dbandeng.response.LoginRequest
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
                        editor.apply()

                        val textToaster = rep
                        print(textToaster)
                        print(AuthToken)

                        Toast.makeText(this@login_user, "${textToaster}", Toast.LENGTH_LONG).show()
                        val landingPageUser = Intent(this@login_user, meow_button_parent::class.java)
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