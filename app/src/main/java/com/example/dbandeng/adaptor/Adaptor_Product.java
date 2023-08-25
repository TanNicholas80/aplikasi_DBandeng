package com.example.dbandeng.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dbandeng.R;
import com.example.dbandeng.modul.ModulProduk;

import java.util.ArrayList;

public class Adaptor_Product extends RecyclerView.Adapter<Adaptor_Product.myViewHolder> {
    ArrayList<ModulProduk> produkArrayList;
    public Adaptor_Product(ArrayList<ModulProduk> produkArrayList) {
        this.produkArrayList = produkArrayList;
    }
    @NonNull
    @Override
    public Adaptor_Product.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recyclerview_product,parent,false);
        return new Adaptor_Product.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptor_Product.myViewHolder holder, int position) {
        holder.nama_produk.setText(produkArrayList.get(position).getNama_produk());
        holder.nama_mitra.setText(produkArrayList.get(position).getNama_mitra());
        holder.harga_produk.setText(produkArrayList.get(position).getHarga_produk());
        holder.foto_produk.setImageResource(R.drawable.ikan_kecil);

    }

    @Override
    public int getItemCount() {
        return produkArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView nama_produk, harga_produk, nama_mitra;
        ImageView foto_produk;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            nama_produk=itemView.findViewById(R.id.crud_namaProduk);
            nama_mitra=itemView.findViewById(R.id.nama_mitra_produk);
            harga_produk=itemView.findViewById(R.id.crud_hargaProduk);
            foto_produk=itemView.findViewById(R.id.crud_imageProduk);
        }
    }
}