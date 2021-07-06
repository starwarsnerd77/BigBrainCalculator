package com.example.bigbraincalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public ArrayList<String> oldMathsList = new ArrayList<>();
    String oldMaths = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        oldMaths = intent.getStringExtra("oldMaths");
        if(oldMaths == null) {
            oldMaths = "";
        }
        Calculator calc = new Calculator();

        createLayouts(calc);

    }

    public void createLayouts(Calculator calc) {
        // Main Layout
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setId(R.id.mainLayout);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        // Calc Buttons Layout
        GridLayout calcButtonsLayout = new GridLayout(this);
        calcButtonsLayout.setBackgroundColor(getResources().getColor(R.color.backColor, null));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        calcButtonsLayout.setLayoutParams(params);
        calcButtonsLayout.setColumnCount(4);

        // Math View
        CalcMathDisplay mathView = new CalcMathDisplay(this);
        mathView.setBackgroundColor(getResources().getColor(R.color.primaryLight, null));
        mathView.setId(R.id.mathDisplay);
        mathView.setMaths(oldMaths);
        mainLayout.addView(mathView);
        ArrayList<CalcButtonData> buttons = createButtonsData();
        createButtons(calc, mathView, calcButtonsLayout, mainLayout, buttons);

        mainLayout.addView(calcButtonsLayout);

        setContentView(mainLayout);
    }

    public void createButtons(final Calculator calc, final CalcMathDisplay mathView, final GridLayout calcButtonsLayout, final LinearLayout mainLayout, ArrayList<CalcButtonData> buttons) {
        AppCompatButton backButton = new AppCompatButton(this);
        backButton.setText("Back");

        for(final CalcButtonData data : buttons) {
            CalcButton button = new CalcButton(this, data, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(data.type == CalcButtonData.ButtonType.INPUT) {
                        mathView.setMaths(mathView.getMaths() + data.text);
                    } else if(data.type == CalcButtonData.ButtonType.CLEAR) {
                        mathView.clearMaths();
                    } else if(data.type == CalcButtonData.ButtonType.HISTORY) {
                        showHistory();
                    } else if(data.type == CalcButtonData.ButtonType.EQUALS) {
                        addMaths(mathView.getMaths(), mainLayout, mathView);
                        mathView.setMaths(calc.evaluateMaths(mathView.getMaths()));
                    } else if(data.type == CalcButtonData.ButtonType.FUNCS) {
                        showFuncs(calc, mathView, calcButtonsLayout, mainLayout);
                    } else if(data.type == CalcButtonData.ButtonType.NUMBERS) {
                        showNumbers(calc, mathView, calcButtonsLayout, mainLayout);
                    } else if(data.type == CalcButtonData.ButtonType.NEG) {
                        mathView.setMaths(calc.toNegative(mathView.getMaths()));
                    } else if(data.type == CalcButtonData.ButtonType.DEL) {
                        mathView.delMaths();
                    } else if(data.type == CalcButtonData.ButtonType.GRAPHING) {
                        showGraph(Float.parseFloat(calc.evaluateMaths(mathView.getMaths())));
                    }
                }
            });
            calcButtonsLayout.addView(button);
        }
    }

    public void showGraph(Float maths) {
        Intent intent = new Intent(MainActivity.this, GraphActivity.class);
        intent.putExtra("maths", maths);
        startActivity(intent);
    }

    public void addMaths(final String oldies, final LinearLayout mainLayout, final CalcMathDisplay mathView) {
        oldMathsList.add(oldies);
    }

    public void showHistory() {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        intent.putExtra("oldMathsList", oldMathsList);
        startActivity(intent);
    }

    public void showFuncs(Calculator calc, final CalcMathDisplay mathView, GridLayout calcButtonsLayout, final LinearLayout mainLayout) {
        calcButtonsLayout.removeAllViews();
        ArrayList<CalcButtonData> buttons = createAltButtonsData();
        createButtons(calc, mathView, calcButtonsLayout, mainLayout, buttons);
    }

    public void showNumbers(Calculator calc, final CalcMathDisplay mathView, GridLayout calcButtonsLayout, final LinearLayout mainLayout) {
        calcButtonsLayout.removeAllViews();
        ArrayList<CalcButtonData> buttons = createButtonsData();
        createButtons(calc, mathView, calcButtonsLayout, mainLayout, buttons);
    }

    public ArrayList<CalcButtonData> createAltButtonsData() {
        ArrayList<CalcButtonData> buttons = new ArrayList<CalcButtonData>() {
            {
                int pd = getResources().getColor(R.color.primaryDark,null);
                int pl = getResources().getColor(R.color.primaryLight,null);
                int pa = getResources().getColor(R.color.primaryAccent,null);
                add(new CalcButtonData("funcs", 0, 1, 1, CalcButtonData.ButtonType.NUMBERS, pa));
                add(new CalcButtonData("history", 0, 2, 1, CalcButtonData.ButtonType.HISTORY, pa));
                add(new CalcButtonData("del", 0, 3, 1, CalcButtonData.ButtonType.DEL, pd));
                add(new CalcButtonData("graphing", 0, 0, 1, CalcButtonData.ButtonType.GRAPHING, pa));
                add(new CalcButtonData("clear", 5, 3, 1, CalcButtonData.ButtonType.CLEAR, pd));
                add(new CalcButtonData("equals", 5, 0, 3, CalcButtonData.ButtonType.EQUALS, pd));
                add(new CalcButtonData("x", 1, 0, 1, pa));
                add(new CalcButtonData("y", 1, 1, 1, pa));
                add(new CalcButtonData(" ^ ", 1, 2, 1, pa));
                add(new CalcButtonData(" + ", 1, 3, 1, pd));
                add(new CalcButtonData("sin", 2, 0, 1, pa));
                add(new CalcButtonData("cos", 2, 1, 1, pa));
                add(new CalcButtonData("tan", 2, 2, 1, pa));
                add(new CalcButtonData(" - ", 2, 3, 1, pd));
                add(new CalcButtonData(" % ", 3, 0, 1, pa));
                add(new CalcButtonData("(", 3, 1, 1, pa));
                add(new CalcButtonData(")", 3, 2, 1, pa));
                add(new CalcButtonData(" X ", 3, 3, 1, pd));
                add(new CalcButtonData("!", 4, 0, 1, pa));
                add(new CalcButtonData("nCr", 4, 1, 1, pa));
                add(new CalcButtonData("nPr", 4, 2, 1, CalcButtonData.ButtonType.NEG, pa));
                add(new CalcButtonData(" / ", 4, 3, 1, pd));
            }
        };
        return buttons;
    }

    public ArrayList<CalcButtonData> createButtonsData() {
        ArrayList<CalcButtonData> buttons = new ArrayList<CalcButtonData>() {
            {
                int pd = getResources().getColor(R.color.primaryDark,null);
                int pl = getResources().getColor(R.color.primaryLight,null);
                int pa = getResources().getColor(R.color.primaryAccent,null);
                add(new CalcButtonData("funcs", 0, 1, 1, CalcButtonData.ButtonType.FUNCS, pa));
                add(new CalcButtonData("history", 0, 2, 1, CalcButtonData.ButtonType.HISTORY, pa));
                add(new CalcButtonData("del", 0, 3, 1, CalcButtonData.ButtonType.DEL, pd));
                add(new CalcButtonData("graphing", 0, 0, 1, CalcButtonData.ButtonType.GRAPHING, pa));
                add(new CalcButtonData("clear", 5, 3, 1, CalcButtonData.ButtonType.CLEAR, pd));
                add(new CalcButtonData("equals", 5, 0, 3, CalcButtonData.ButtonType.EQUALS, pd));
                add(new CalcButtonData("7", 1, 0, 1, pa));
                add(new CalcButtonData("8", 1, 1, 1, pa));
                add(new CalcButtonData("9", 1, 2, 1, pa));
                add(new CalcButtonData(" + ", 1, 3, 1, pd));
                add(new CalcButtonData("4", 2, 0, 1, pa));
                add(new CalcButtonData("5", 2, 1, 1, pa));
                add(new CalcButtonData("6", 2, 2, 1, pa));
                add(new CalcButtonData(" - ", 2, 3, 1, pd));
                add(new CalcButtonData("1", 3, 0, 1, pa));
                add(new CalcButtonData("2", 3, 1, 1, pa));
                add(new CalcButtonData("3", 3, 2, 1, pa));
                add(new CalcButtonData(" X ", 3, 3, 1, pd));
                add(new CalcButtonData(".", 4, 0, 1, pa));
                add(new CalcButtonData("0", 4, 1, 1, pa));
                add(new CalcButtonData("neg", 4, 2, 1, CalcButtonData.ButtonType.NEG, pa));
                add(new CalcButtonData(" / ", 4, 3, 1, pd));

            }
        };
        return buttons;
    }
}