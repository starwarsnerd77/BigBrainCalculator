package com.example.bigbraincalculator;

import android.graphics.Color;
import android.widget.Button;

public class CalcButtonData {
    public String text;
    public int row;
    public int col;
    public int colSpan;
    public ButtonType type;
    public int color;

    public enum ButtonType {
        INPUT,
        ACTION,
        CLEAR,
        HISTORY,
        EQUALS,
        FUNCS,
        NEG,
        DEL,
        GRAPHING
    }
    public CalcButtonData(String text, int row, int col, int colSpan, ButtonType type, int color) {
        this.text = text;
        this.row = row;
        this.col = col;
        this.colSpan = colSpan;
        this.type = type;
        this.color = color;
    }

    public CalcButtonData(String text, int row, int col, int colSpan, int color) {
        this.text = text;
        this.row = row;
        this.col = col;
        this.colSpan = colSpan;
        this.type = ButtonType.INPUT;
        this.color = color;
    }
}
