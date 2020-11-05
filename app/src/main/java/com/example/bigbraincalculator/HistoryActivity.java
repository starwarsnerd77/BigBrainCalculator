package com.example.bigbraincalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;


public class HistoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ArrayList<String> oldMathsList = intent.getStringArrayListExtra("oldMathsList");

        ScrollView historyPage = new ScrollView(this);
        LinearLayout mathsHistory = new LinearLayout(this);
        mathsHistory.setOrientation(LinearLayout.VERTICAL);
        for(final String oldies : oldMathsList) {
            AppCompatButton mathButton = new AppCompatButton(this);
            mathButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(HistoryActivity.this, MainActivity.class);
                    intent1.putExtra("oldMaths", oldies);
                    startActivity(intent1);
                }
            });
            mathButton.setText(oldies);
            mathsHistory.addView(mathButton);
        }
        historyPage.addView(mathsHistory);

        setContentView(historyPage);


    }
}
