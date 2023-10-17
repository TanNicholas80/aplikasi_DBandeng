package com.example.dbandeng;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dbandeng.adaptor.CRUD_AdaptorProduk;
import com.example.dbandeng.modul.ModulMitra;
import com.example.dbandeng.modul.ModulProduk;
import com.example.dbandeng.modul.ModulProdukNew;
import com.example.dbandeng.response.CreateProdukResponse;
import com.example.dbandeng.response.GetProductResponse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.prefs.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CRUD_Product extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    ModulProduk modulProdukDump;
    Button tblCreateProduk;
    String authToken;
    ArrayList<ModulProdukNew> produkArrayList=new ArrayList<>();
    CRUD_AdaptorProduk adaptorProduk;
    private Toolbar CRUDToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_product);

        tblCreateProduk = findViewById(R.id.crud_btnTambah);
        tblCreateProduk.setOnClickListener(this);

        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        authToken = preferences.getString("auth_token", null);
        String idMitra = preferences.getString("id_mitra", null);
        recyclerView=findViewById(R.id.recyclerProduk);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        try{
            getDataProdukMitra(authToken,idMitra);

        }catch (Exception e){
            Log.d("crud_produk", e.getMessage());
        }

        // setup action bar
        CRUDToolbar = findViewById(R.id.CRUD_toolbar);
        setSupportActionBar(CRUDToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Beranda");
    }

    public void getDataProdukMitra(String authToken, String idMitra){
        InterfaceDbandeng interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng.class);
        Call<GetProductResponse> getProduk = interfaceDbandeng.getProdukMitra("Bearer " + authToken, idMitra);

        getProduk.enqueue(new Callback<GetProductResponse>() {
            @Override
            public void onResponse(Call<GetProductResponse> call, Response<GetProductResponse> response) {
                String responseData = response.body().getData();
                Gson gson = new Gson();
                ModulMitra modelMitra = gson.fromJson(responseData, ModulMitra.class);
                ArrayList<ModulProdukNew> produkMitra = new ArrayList<>(modelMitra.getProducts());
                CRUD_AdaptorProduk adaptorProduk=new CRUD_AdaptorProduk(produkMitra, authToken);
                produkArrayList = new ArrayList<>(modelMitra.getProducts());
                adaptorProduk=new CRUD_AdaptorProduk(produkArrayList, authToken);
                recyclerView.setAdapter(adaptorProduk);
            }

            @Override
            public void onFailure(Call<GetProductResponse> call, Throwable t) {
                Toast.makeText(CRUD_Product.this,"gagal get produk" + t.getMessage(), Toast.LENGTH_LONG);
                Log.d("crud_produk", "error" + t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        CRUD_AdaptorProduk adaptorCRUDProduk = new CRUD_AdaptorProduk(produkArrayList, authToken);
        adaptorProduk = new CRUD_AdaptorProduk(produkArrayList, authToken);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin, menu);
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
                ArrayList<ModulProdukNew> searchList = new ArrayList<>();
                Log.d("test123",newText );
                if(produkArrayList.isEmpty()){
                    return true;
                }
                if (newText != null) {
                    for (ModulProdukNew i : produkArrayList) {
                        if (i.getNmProduk().toLowerCase(Locale.ROOT).contains(newText)) {
                            searchList.add(i);
                            Log.d("test123",i.getNmProduk().toLowerCase(Locale.ROOT)+ "==="+newText );

                        }
                    }
                    if (searchList.isEmpty()) {
                        Toast.makeText(CRUD_Product.this, "Data Produk Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("test123",searchList.toString() );
                        adaptorProduk.onApplySearch(searchList);
                    }
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.crud_btnTambah) {
            startActivity(new Intent(CRUD_Product.this,createproduct.class));
        }
    }
}