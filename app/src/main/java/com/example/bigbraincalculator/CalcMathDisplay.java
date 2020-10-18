package com.example.bigbraincalculator;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CalcMathDisplay extends AppCompatTextView {
    private String maths = "";
    public CalcMathDisplay(Context context) {
        super(context);
        setGravity(Gravity.CENTER);
        setPadding(30, 300, 30, 300);
        setTextSize(20);
    }

    public void setMaths(String maths) {
        this.maths = maths;
        setText(maths);
    }

    public String getMaths() {
        return maths;
    }

    public void clearMaths() {
        maths = "";
        setMaths(maths);
    }

    public void delMaths() {
        ArrayList<String> newMaths = new ArrayList<String>() {
            {
                for(String c : maths.split(" ")) {
                    add(c);
                }
            }
        };
        if(newMaths.size() <= 1) {
            clearMaths();
        } else {
            newMaths.remove(newMaths.size() - 1);
            maths = String.join(" ", newMaths);
            if(newMaths.get(newMaths.size() - 1).equals("+") || newMaths.get(newMaths.size() - 1).equals("-") || newMaths.get(newMaths.size() - 1).equals("x") || newMaths.get(newMaths.size() - 1).equals("/")) {
                maths += " ";
            }
            setMaths(maths);
        }

    }


}
