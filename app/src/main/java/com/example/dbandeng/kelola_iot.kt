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
import com.example.dbandeng.modul.ModulLogIOT
import com.example.dbandeng.utils.SetDataChart
import com.example.dbandeng.utils.SocketInstance
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Collections
import java.util.Date
import java.util.Locale


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
    var firstConnection: Boolean= true
    var dataLogIOT: ArrayList<ModulLogIOT> = ArrayList<ModulLogIOT>()
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



        //setData = SetDataChart(barChart);
        //setData.useDumpDataChart();

        socketHandler = SocketInstance().getmSocket()
        socketHandler.connect()

        socketHandler.on("auth_iot", onNewMessageAuth)
        socketHandler.on("log_devices/res", onNewMessageLogIOT)

        socketHandler.emit("log_devices", "$idMitra")


        btnSendToken.setOnClickListener{
            iotConnectStatus.setText("Loading...")
            sendSocketTokenIOT()
        }

        switchConveyor.setOnCheckedChangeListener { buttonView, isChecked ->
            if(!firstConnection){
                sendSocketPerintahIOT(isChecked)
            }
        }

        socketHandler.on("cmd_iot", onNewMessagePerintah)


        setSupportActionBar(kelolaToolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Beranda"
    }

    private fun getDate(dateString: String): Date {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        Log.d("test123", dateFormat.toString() + " " + dateString)
        return dateFormat.parse(dateString)
    }

    fun formatHoursMinutes(hoursWork: Float): String {
        val hours = hoursWork.toInt()
        val minutes = ((hoursWork - hours) * 60).toInt()
        return String.format("%02d:%02d", hours, minutes)
    }
    fun initBarChart(dataList: ArrayList<ModulLogIOT>){
        dataList.sortWith(Comparator { entry1, entry2 ->
            val date1 = getDate(entry1.tanggal)
            val date2 = getDate(entry2.tanggal)
            date1.compareTo(date2)
        })
        val entries = ArrayList<BarEntry>()
        val labels = ArrayList<String>()

        for (i in dataList.indices) {
            entries.add(BarEntry(i.toFloat(), dataList[i].hours_work.toFloat()))
            labels.add(dataList[i].tanggal)
            Log.d("test123", dataList[i].tanggal.toString() + " "+dataList[i].hours_work.toString() )
        }

        val barDataSet = BarDataSet(entries, "Jam Kerja")

        val dataSets = ArrayList<IBarDataSet>()
        dataSets.add(barDataSet)

        val barData = BarData(dataSets)
        barChart.data = barData

        //setData = SetDataChart(barChart);
        //setData.useDumpDataChart();

        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.labelCount = labels.size
        xAxis.labelRotationAngle = 0f

        val valueFormatter = object : ValueFormatter() {
            override fun getBarLabel(barEntry: BarEntry): String {
                return formatHoursMinutes(barEntry.y)
            }
        }
        barDataSet.valueFormatter = valueFormatter
        barDataSet.valueTextSize = 12f
        barDataSet.valueTextColor = resources.getColor(android.R.color.black)

        barChart.setFitBars(true)
        barChart.animateY(2000)
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
        runOnUiThread(kotlinx.coroutines.Runnable{
            if(data == "success"){
                switchConveyor.isChecked = true
                iotConnectStatus.setText("Perangkat berhasil terkoneksi!")
                firstConnection = false
            }else{
                iotConnectStatus.setText("Gagal terkoneksi, harap coba lagi")
            }
        })
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
                socketHandler.emit("log_devices", "$idMitra")
            }

        })
    }

    private val onNewMessageLogIOT = Emitter.Listener { args ->
        runOnUiThread(kotlinx.coroutines.Runnable {

            val gson = Gson()
            val listType: Type = object : TypeToken<ArrayList<ModulLogIOT?>?>() {}.type
            val dataList: ArrayList<ModulLogIOT> = gson.fromJson(args[0].toString(), listType)
            dataLogIOT = dataList
            initBarChart(dataList)
            Log.d("sockettest",dataList[0].toString())
        })

    }




}