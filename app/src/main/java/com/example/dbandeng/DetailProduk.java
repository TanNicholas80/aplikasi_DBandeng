package com.example.dbandeng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;

import com.example.dbandeng.adaptor.AdaptorListImage;
import com.example.dbandeng.adaptor.CRUD_AdaptorProduk;
import com.example.dbandeng.modul.ModulProduk;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

public class DetailProduk extends AppCompatActivity {
    Toolbar tb_detail_produk;
    RecyclerView recyclerView;
    ArrayList<String> imageArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        // setup Action Bar
        tb_detail_produk = findViewById(R.id.toolbar_detail_produk);
        setSupportActionBar(tb_detail_produk);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Produk");
        // setup recycler
        for (int i = 0; i < 15; i++){
            imageArrayList.add("ABC");
        }

        recyclerView=findViewById(R.id.recycler_list_thumbnail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        AdaptorListImage adaptorImage=new AdaptorListImage(imageArrayList);
        recyclerView.setAdapter(adaptorImage);
    }
}