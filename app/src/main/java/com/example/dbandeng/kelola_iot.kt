package com.example.dbandeng

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import com.example.dbandeng.utils.SetDataChart
import com.example.dbandeng.utils.SocketInstance
import com.github.mikephil.charting.charts.BarChart
import io.socket.client.Socket
import io.socket.emitter.Emitter

class kelola_iot : AppCompatActivity() {
    private lateinit var kelolaToolbar: Toolbar
    private lateinit var barChart: BarChart
    private lateinit var setData: SetDataChart
    private lateinit var socketHandler: Socket
    private lateinit var edTokenIOT: EditText
    private lateinit var iotConnectStatus: TextView
    private lateinit var btnSendToken: Button
    private lateinit var idMitra: String
    private lateinit var tokenIot: String
    private lateinit var statusConveyor: TextView
    private lateinit var iconStatusConveyor: ImageView
    private lateinit var switchConveyor: SwitchCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kelola_iot)
        // setup action bar
        kelolaToolbar = findViewById(R.id.kelola_iot_toolbar)
        barChart = findViewById(R.id.barChart)
        edTokenIOT = findViewById(R.id.ED_token_iot)
        btnSendToken = findViewById(R.id.BTN_koneksi_IOT)
        iotConnectStatus = findViewById(R.id.status_IOT)
        statusConveyor = findViewById(R.id.statusConveyor)
        iconStatusConveyor = findViewById(R.id.iconStatusConveyor)
        switchConveyor = findViewById(R.id.switchConveyor)

        val preferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
        idMitra = preferences.getString("id_mitra", null).toString();

        setData = SetDataChart(barChart);
        setData.useDumpDataChart();

        socketHandler = SocketInstance().getmSocket()
        socketHandler.connect()

        socketHandler.on("auth_iot", onNewMessageAuth)



        btnSendToken.setOnClickListener{
            iotConnectStatus.setText("Loading...")
            sendSocketTokenIOT()
        }

        switchConveyor.setOnCheckedChangeListener { buttonView, isChecked ->
            sendSocketPerintahIOT(isChecked)
        }

        socketHandler.on("cmd_iot", onNewMessagePerintah)


        setSupportActionBar(kelolaToolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Beranda"
    }

    fun sendSocketTokenIOT(){
        tokenIot= edTokenIOT.text.toString()
        if(tokenIot.isEmpty()){
            return;
        }
        socketHandler.emit("message", "AUTH;$tokenIot;$idMitra")
        edTokenIOT.text.clear()
    }

    fun sendSocketPerintahIOT(isChecked:Boolean){
        if(isChecked){
            statusConveyor.setText("Menyalakan perangkat...")
            socketHandler.emit("message", "PERINTAH;$tokenIot;ON")
        }else{
            statusConveyor.setText("Mematikan perangkat...")
            socketHandler.emit("message", "PERINTAH;$tokenIot;OFF")
        }

    }

    private val onNewMessageAuth = Emitter.Listener { args ->
        val data = args[0]

        if(data == "success"){
            iotConnectStatus.setText("Perangkat berhasil terkoneksi!")
        }else{
            iotConnectStatus.setText("Gagal terkoneksi, harap coba lagi")
        }
        Log.d("socket",data.toString())
    }

    private val onNewMessagePerintah = Emitter.Listener { args ->
        runOnUiThread(kotlinx.coroutines.Runnable {
            val data = args[0]
            Log.d("sockettest",data.toString())
            if(data == "successon"){
                statusConveyor.setText("Nyala")
                iconStatusConveyor.setImageResource(R.drawable.power_on)
            }else if(data == "successoff"){
                statusConveyor.setText("Mati")
                iconStatusConveyor.setImageResource(R.drawable.power_off)
            }
        })
    }




}