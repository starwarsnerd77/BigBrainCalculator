package com.example.bigbraincalculator;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

public class OldMaths extends AppCompatButton {
    public OldMaths(Context context, String oldies, OnClickListener onClickListener) {
        super(context);
        setText(oldies);
    }
}
