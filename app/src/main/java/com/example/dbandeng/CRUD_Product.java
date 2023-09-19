package com.example.dbandeng;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dbandeng.adaptor.CRUD_AdaptorProduk;
import com.example.dbandeng.modul.ModulProduk;

import java.util.ArrayList;
import java.util.Locale;

public class CRUD_Product extends AppCompatActivity {
    RecyclerView recyclerView;
    ModulProduk modulProdukDump;
    ArrayList<ModulProduk> produkArrayList=new ArrayList<>();
    private Toolbar CRUDToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_product);
        // setup Data RecyclerView
        ModulProduk modulProdukDump= new ModulProduk("1","Bandeng Enak", "Sholeh AC", "5cm", "20", "50000", "") ;
        for (int i = 0; i < 15; i++){
            produkArrayList.add(modulProdukDump);
        }
        recyclerView=findViewById(R.id.recyclerProduk);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CRUD_AdaptorProduk adaptorProduk=new CRUD_AdaptorProduk(produkArrayList);
        recyclerView.setAdapter(adaptorProduk);
        // setup action bar
        CRUDToolbar = findViewById(R.id.CRUD_toolbar);
        setSupportActionBar(CRUDToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Beranda");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        CRUD_AdaptorProduk adaptorCRUDProduk = new CRUD_AdaptorProduk(produkArrayList);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dbandeng, menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Cari Produk ...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<ModulProduk> searchList = new ArrayList<>();

                if (newText != null) {
                    for (ModulProduk i : produkArrayList) {
                        if (i.getNama_produk().toLowerCase(Locale.ROOT).contains(newText)) {
                            searchList.add(i);
                        }
                    }
                    if (searchList.isEmpty()) {
                        Toast.makeText(CRUD_Product.this, "Data Produk Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                    } else {
                        adaptorCRUDProduk.onApplySearch(searchList);
                    }
                }
                return true;
            }
        });
        return true;
    }
}