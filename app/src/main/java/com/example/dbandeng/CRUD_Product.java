package com.example.dbandeng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dbandeng.modul.ModulProduk;

import java.util.ArrayList;

public class CRUD_Product extends AppCompatActivity {
    RecyclerView recyclerView;
    ModulProduk modulProdukDump;
    ArrayList<ModulProduk> produkArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_product);

        ModulProduk modulProdukDump= new ModulProduk("1","Bandeng Enak", "5cm", "20", "50000") ;
        for (int i = 0; i < 15; i++){
            produkArrayList.add(modulProdukDump);
        }
        recyclerView=findViewById(R.id.recyclerProduk);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CRUD_AdaptorProduk adaptorProduk=new CRUD_AdaptorProduk(produkArrayList);
        recyclerView.setAdapter(adaptorProduk);
    }
}