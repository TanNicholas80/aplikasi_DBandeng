package com.example.dbandeng;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.cloudinary.android.MediaManager;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;
import android.Manifest;

import java.util.HashMap;
import java.util.Map;


public class createproduct extends AppCompatActivity {
    EditText inputNamaProduk, inputHrgProduk, inputStokProduk, inputBeratProduk, inputDskProduk, inputLinkProduk;
    ImageView inputFotoProduk;
    Button btnBatal, btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createproduct);

        inputNamaProduk = findViewById(R.id.namaProduk);
        inputFotoProduk = findViewById(R.id.fotoProduk);
        inputHrgProduk = findViewById(R.id.hargaProduk);
        inputStokProduk = findViewById(R.id.stokProduk);
        inputBeratProduk = findViewById(R.id.beratProduk);
        inputDskProduk = findViewById(R.id.deskripsiProduk);
        inputLinkProduk = findViewById(R.id.linkProduk);

        btnBatal = findViewById(R.id.Btn_Batal_Create);
        btnSimpan = findViewById(R.id.Btn_Simpan_Produk);

        inputFotoProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(createproduct.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .galleryOnly()          // Mengambil dari galeri saja
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        inputFotoProduk.setImageURI(uri);
    }


}
