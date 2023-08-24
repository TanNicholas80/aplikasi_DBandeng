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

public class AdaptorListImage extends RecyclerView.Adapter<AdaptorListImage.myViewHolder> {
    ArrayList<String> imageArrayList;
    public AdaptorListImage(ArrayList<String> imageArrayList) {
        this.imageArrayList = imageArrayList;
    }
    @NonNull
    @Override
    public AdaptorListImage.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.format_thumbnail_image,parent,false);
        return new AdaptorListImage.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptorListImage.myViewHolder holder, int position) {
        holder.foto_produk.setImageResource(R.drawable.ikan_kecil);

    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView foto_produk;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            foto_produk=itemView.findViewById(R.id.thumbnail_produk);
        }
    }
}
