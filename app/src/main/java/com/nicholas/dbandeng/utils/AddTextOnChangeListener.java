package com.nicholas.dbandeng.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class AddTextOnChangeListener implements TextWatcher {
    private Context mContext;
    EditText mEdittextview;

    public AddTextOnChangeListener(Context context, EditText edittextview) {
        super();
        this.mContext = context;
        this.mEdittextview= edittextview;
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //What you want to do
    }
}

