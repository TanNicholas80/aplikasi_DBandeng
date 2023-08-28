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

public class CRUD_AdaptorProduk extends RecyclerView.Adapter<CRUD_AdaptorProduk.myViewHolder> {
    ArrayList<ModulProduk> produkArrayList;
    public CRUD_AdaptorProduk(ArrayList<ModulProduk> produkArrayList) {
        this.produkArrayList = produkArrayList;
    }
    @NonNull
    @Override
    public CRUD_AdaptorProduk.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.format_crud_produk,parent,false);
        return new CRUD_AdaptorProduk.myViewHolder(view);
    }

    public void onApplySearch(ArrayList<ModulProduk> produkArrayList) {
        this.produkArrayList = produkArrayList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CRUD_AdaptorProduk.myViewHolder holder, int position) {
        holder.nama_produk.setText(produkArrayList.get(position).getNama_produk());
        holder.keterangan_produk.setText("Ukuran : " + produkArrayList.get(position).getKeterangan_produk());
        holder.stok_produk.setText("Stok : "+produkArrayList.get(position).getStok_produk());
        holder.foto_produk.setImageResource(R.drawable.ikan_kecil);

    }

    @Override
    public int getItemCount() {
        return produkArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView nama_produk, keterangan_produk, stok_produk;
        ImageView foto_produk;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            nama_produk=itemView.findViewById(R.id.crud_namaProduk);
            keterangan_produk=itemView.findViewById(R.id.crud_keteranganProduk);
            stok_produk=itemView.findViewById(R.id.crud_stokProduk);
            foto_produk=itemView.findViewById(R.id.crud_imageProduk);
        }
    }
}
