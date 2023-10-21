package com.nicholas.dbandeng

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholas.dbandeng.adaptor.AdaptorLaporan
import com.nicholas.dbandeng.interfaceAPI.InterfaceNodeJs
import com.nicholas.dbandeng.koneksi.KoneksiNodeJs
import com.nicholas.dbandeng.modul.ModulLaporan
import com.nicholas.dbandeng.response.ResponseNodeJs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class hasil_iot : AppCompatActivity() {
    private lateinit var hasilToolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var laporanArrayList: ArrayList<ModulLaporan>
    private lateinit var adaptorLaporan: AdaptorLaporan
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_iot)
        // setup action bar
        hasilToolbar = findViewById(R.id.hasil_iot_toolbar)
        recyclerView = findViewById(R.id.recycler_laporan)

        val preferences = getSharedPreferences("my_preferences", AppCompatActivity.MODE_PRIVATE)
        val idMitra = preferences.getString("id_mitra", null).toString();
        recyclerView?.setLayoutManager(LinearLayoutManager(this))

        //dataDump()
        getLaporanAvailable(idMitra)

        setSupportActionBar(hasilToolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Beranda"
    }
    fun getLaporanAvailable(id : String) {
        val interfaceNodeJs = KoneksiNodeJs.Koneksi().create(InterfaceNodeJs::class.java)
        val getLaporan = interfaceNodeJs.getAvailableLaporan(id)
        getLaporan.enqueue(object : Callback<ResponseNodeJs> {
            override fun onResponse(call: Call<ResponseNodeJs>, response: Response<ResponseNodeJs>) {
                Log.d("hasil", call.toString())
                Log.d("hasil", response.body().toString())
                val responseData: ArrayList<ModulLaporan> = response.body()!!.message
                laporanArrayList = ArrayList<ModulLaporan>(responseData)
                adaptorLaporan = AdaptorLaporan(laporanArrayList)
                recyclerView!!.setAdapter(adaptorLaporan)
                //dataDump()
            }

            override fun onFailure(call: Call<ResponseNodeJs>, t: Throwable) {
                Toast.makeText(this@hasil_iot, "gagal get laporan" + t.message, Toast.LENGTH_LONG).show()
                Log.d("hasil", "error" + t.message)
                Log.d("hasil", "error" + call.toString())
            }
        })
    }
}