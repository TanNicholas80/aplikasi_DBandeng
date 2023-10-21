package com.nicholas.dbandeng

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dx.dxloadingbutton.lib.LoadingButton
import com.nicholas.dbandeng.modul.ModulUser
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class register_user : AppCompatActivity(), View.OnClickListener {
    lateinit var inputNama: TextInputLayout
    lateinit var inputEmail: TextInputLayout
    lateinit var inputPass: TextInputLayout
    lateinit var btnReg_user: LoadingButton
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
            btnReg_user.startLoading()
            val xNama = inputNama.editText?.text.toString()
            val xEmail = inputEmail.editText?.text.toString()
            val xPass = inputPass.editText?.text.toString()

            interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java);
            val login: Call<ModulUser>? = interfaceDbandeng?.registerUser(xNama,xEmail,xPass)
            login?.enqueue(object : Callback<ModulUser>{
                override fun onResponse(call: Call<ModulUser>, response: Response<ModulUser>) {
                    if (response.isSuccessful) {
                        btnReg_user.loadingSuccessful()
                        Toast.makeText(this@register_user, "Berhasil Register user", Toast.LENGTH_LONG).show()
                        val layoutLogin = Intent(this@register_user, login_user::class.java)
                        startActivity(layoutLogin);
                    } else {
                        btnReg_user.loadingFailed()
                        Toast.makeText(this@register_user, "Gagal Login", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<ModulUser>, t: Throwable) {
                    btnReg_user.loadingFailed()
                    Log.d("RegisUser", t.message.toString());
                    Toast.makeText(this@register_user, "Gagal" + t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}