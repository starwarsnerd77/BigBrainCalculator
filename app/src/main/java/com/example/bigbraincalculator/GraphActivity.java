package com.example.bigbraincalculator;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class GraphActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Float maths = intent.getFloatExtra("maths", 0);
        Graph graph = new Graph(this, maths);
        setContentView(graph);
    }
}
