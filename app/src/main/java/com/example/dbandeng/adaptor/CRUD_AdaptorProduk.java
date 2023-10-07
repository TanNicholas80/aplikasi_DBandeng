package com.example.dbandeng.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbandeng.*;
import com.example.dbandeng.modul.ModulProduk;
import com.example.dbandeng.modul.ModulProdukNew;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CRUD_AdaptorProduk extends RecyclerView.Adapter<CRUD_AdaptorProduk.myViewHolder> {
    ArrayList<ModulProdukNew> produkArrayList;
    InterfaceDbandeng interfaceDbandeng;
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
//        holder.card_produk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                Intent intent = new Intent(holder.itemView.getContext(), edit_produk.class);
//                intent.putExtra("id_produk", produkArrayList.get(position).getId());
//                intent.putExtra("nama_produk", produkArrayList.get(position).getNmProduk());
//                intent.putExtra("berat_produk", produkArrayList.get(position).getBeratProduk());
//                intent.putExtra("stok_produk", produkArrayList.get(position).getStok());
//                intent.putExtra("harga_produk", produkArrayList.get(position).getHrgProduk());
//                intent.putExtra("foto_produk", produkArrayList.get(position).getFoto_produk());
//                intent.putExtra("link_produk", produkArrayList.get(position).getLink());
//                intent.putExtra("dsk_produk", produkArrayList.get(position).getDskProduk());
//
//                // Start activity dengan intent
//                holder.itemView.getContext().startActivity(intent);
//            }
//        });
        holder.tblEditProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(holder.itemView.getContext(), edit_produk.class);
                intent.putExtra("id_produk", produkArrayList.get(position).getId());
                intent.putExtra("nama_produk", produkArrayList.get(position).getNmProduk());
                intent.putExtra("berat_produk", produkArrayList.get(position).getBeratProduk());
                intent.putExtra("stok_produk", produkArrayList.get(position).getStok());
                intent.putExtra("harga_produk", produkArrayList.get(position).getHrgProduk());
                intent.putExtra("foto_produk", produkArrayList.get(position).getFoto_produk());
                intent.putExtra("link_produk", produkArrayList.get(position).getLink());
                intent.putExtra("dsk_produk", produkArrayList.get(position).getDskProduk());

                // Start activity dengan intent
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.tblDeleteProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(holder.itemView.getContext(), CRUD_Product.class);
                intent.putExtra("id_produk", produkArrayList.get(position).getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produkArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView nama_produk, keterangan_produk, stok_produk, harga_produk;
        ImageView foto_produk;
//        CardView card_produk;
        ImageButton tblEditProduk, tblDeleteProduk;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            nama_produk=itemView.findViewById(R.id.crud_namaProduk);
            keterangan_produk=itemView.findViewById(R.id.crud_keteranganProduk);
            stok_produk=itemView.findViewById(R.id.crud_stokProduk);
            foto_produk=itemView.findViewById(R.id.crud_imageProduk);
            harga_produk=itemView.findViewById(R.id.crud_hargaProduk);
//            card_produk=itemView.findViewById(R.id.card_CRUD_Product);
            tblEditProduk=itemView.findViewById(R.id.crud_btnEdit);
            tblDeleteProduk=itemView.findViewById(R.id.crud_btnHapus);
        }
    }

    public void popUpDelete() {
        interfaceDbandeng= koneksiAPI.Koneksi().create(InterfaceDbandeng.class);

//        val interfaceDbandeng = koneksiAPI.Koneksi().create(InterfaceDbandeng::class.java)
//        val editPopUp = Dialog(context)
//        editPopUp.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        editPopUp.setCancelable(false)
//        editPopUp.setContentView(R.layout.layout_popup_edit)
//        editPopUp.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        editPopUp.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}
