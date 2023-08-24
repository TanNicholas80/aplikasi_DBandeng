package com.example.dbandeng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dbandeng.adaptor.AdaptorListImage;
import com.example.dbandeng.adaptor.CRUD_AdaptorProduk;
import com.example.dbandeng.modul.ModulProduk;

import java.util.ArrayList;

public class DetailProduk extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> imageArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        for (int i = 0; i < 15; i++){
            imageArrayList.add("ABC");
        }

        recyclerView=findViewById(R.id.recycler_list_thumbnail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        AdaptorListImage adaptorImage=new AdaptorListImage(imageArrayList);
        recyclerView.setAdapter(adaptorImage);
    }
}