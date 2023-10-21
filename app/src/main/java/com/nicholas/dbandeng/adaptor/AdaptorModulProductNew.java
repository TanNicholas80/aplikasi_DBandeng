package com.nicholas.dbandeng.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nicholas.dbandeng.DetailProduk;
import com.nicholas.dbandeng.R;
import com.nicholas.dbandeng.modul.ModulProduk;
import com.nicholas.dbandeng.modul.ModulProdukNew;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptorModulProductNew extends RecyclerView.Adapter<AdaptorModulProductNew.myViewHolder> {
    ArrayList<ModulProdukNew> produkArrayList;
    String namaMitra;
    int limit;
    boolean isLimited = false;
    public AdaptorModulProductNew(ArrayList<ModulProdukNew> produkArrayList, String namaMitra) {
        this.produkArrayList = produkArrayList;
        this.limit = -1;
        this.namaMitra = namaMitra;
    }

    public void setLimit(int limit){
        this.isLimited = true;
        this.limit = limit;
    }

    public void onApplySearch(ArrayList<ModulProdukNew> filteredProduct) {
        produkArrayList = filteredProduct;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdaptorModulProductNew.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recyclerview_product,parent,false);
        return new AdaptorModulProductNew.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptorModulProductNew.myViewHolder holder, int position) {
        holder.nama_produk.setText(produkArrayList.get(position).getNmProduk());
        holder.nama_mitra.setText(namaMitra);
        holder.harga_produk.setText("Rp. " + produkArrayList.get(position).getHrgProduk());
        Picasso.get().load(produkArrayList.get(position).getFoto_produk()).resize(300, 300).into(holder.foto_produk);
        holder.Card_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(holder.itemView.getContext(), DetailProduk.class);
                intent.putExtra("id_produk", produkArrayList.get(position).getId());
                intent.putExtra("nama_produk", produkArrayList.get(position).getNmProduk());
                intent.putExtra("nama_mitra", namaMitra);
                intent.putExtra("foto_produk", produkArrayList.get(position).getFoto_produk());
                intent.putExtra("desk_produk", produkArrayList.get(position).getDskProduk());
                intent.putExtra("harga_produk", produkArrayList.get(position).getHrgProduk());
                intent.putExtra("link_produk", produkArrayList.get(position).getLink());

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(isLimited){
            return Math.min(produkArrayList.size(), limit);
        }else{
            return produkArrayList.size();
        }
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView nama_produk, harga_produk, nama_mitra;
        ImageView foto_produk;
        CardView Card_Product;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            nama_produk=itemView.findViewById(R.id.crud_namaProduk);
            nama_mitra=itemView.findViewById(R.id.nama_mitra_produk);
            harga_produk=itemView.findViewById(R.id.crud_hargaProduk);
            foto_produk=itemView.findViewById(R.id.crud_imageProduk);
            Card_Product=itemView.findViewById(R.id.CardProduct);
        }
    }
}
