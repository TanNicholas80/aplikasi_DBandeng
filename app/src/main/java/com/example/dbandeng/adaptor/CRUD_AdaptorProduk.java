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
import com.example.dbandeng.modul.ModulProdukNew;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CRUD_AdaptorProduk extends RecyclerView.Adapter<CRUD_AdaptorProduk.myViewHolder> {
    ArrayList<ModulProdukNew> produkArrayList;
    public CRUD_AdaptorProduk(ArrayList<ModulProdukNew> produkArrayList) {
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

    public void onApplySearch(ArrayList<ModulProdukNew> produkArrayList) {
        this.produkArrayList = produkArrayList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CRUD_AdaptorProduk.myViewHolder holder, int position) {
        holder.nama_produk.setText(produkArrayList.get(position).getNmProduk());
        holder.keterangan_produk.setText("Ukuran : " + produkArrayList.get(position).getBeratProduk());
        holder.stok_produk.setText("Stok : "+produkArrayList.get(position).getStok());
        holder.harga_produk.setText("Rp." + produkArrayList.get(position).getHrgProduk());
        Picasso.get().load(produkArrayList.get(position).getFoto_produk()).resize(300, 300).into(holder.foto_produk);
    }

    @Override
    public int getItemCount() {
        return produkArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView nama_produk, keterangan_produk, stok_produk, harga_produk;
        ImageView foto_produk;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            nama_produk=itemView.findViewById(R.id.crud_namaProduk);
            keterangan_produk=itemView.findViewById(R.id.crud_keteranganProduk);
            stok_produk=itemView.findViewById(R.id.crud_stokProduk);
            foto_produk=itemView.findViewById(R.id.crud_imageProduk);
            harga_produk=itemView.findViewById(R.id.crud_hargaProduk);
        }
    }
}
