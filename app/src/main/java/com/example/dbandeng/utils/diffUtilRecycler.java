package com.example.dbandeng.utils;

import androidx.recyclerview.widget.DiffUtil;
import com.example.dbandeng.modul.ModulProdukNew;

import java.util.ArrayList;

public class diffUtilRecycler extends DiffUtil.Callback {

    private ArrayList<ModulProdukNew> oldList;
    private ArrayList<ModulProdukNew> newList;

    public diffUtilRecycler(ArrayList<ModulProdukNew> oldList, ArrayList<ModulProdukNew> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItemPosition == newItemPosition;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }
}
