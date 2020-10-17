package com.example.bigbraincalculator;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.appcompat.widget.AppCompatButton;

public class CalcButton extends AppCompatButton {
    public CalcButton(Context context, CalcButtonData data, OnClickListener onClickListener) {
        super(context);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(data.row, 1, 1);
        params.columnSpec = GridLayout.spec(data.col, data.colSpan, 1);
        params.setMargins(1,1,1,1);
        setLayoutParams(params);
        setText(data.text);
        setBackgroundColor(data.color);
        setOnClickListener(onClickListener);
    }
}
