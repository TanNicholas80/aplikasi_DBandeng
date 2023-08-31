package com.example.dbandeng

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.dbandeng.utils.SetDataChart
import com.github.mikephil.charting.charts.BarChart

class kelola_iot : AppCompatActivity() {
    private lateinit var kelolaToolbar: Toolbar
    private lateinit var barChart: BarChart
    private lateinit var setData: SetDataChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kelola_iot)
        // setup action bar
        kelolaToolbar = findViewById(R.id.kelola_iot_toolbar)
        barChart = findViewById(R.id.barChart)

        setData = SetDataChart(barChart);
        setData.useDumpDataChart();


        setSupportActionBar(kelolaToolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Beranda"
    }
}