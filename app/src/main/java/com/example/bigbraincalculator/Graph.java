package com.example.bigbraincalculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Graph extends View {

    private Float maths;
    private String sMaths;
    private int selector;

    public Graph(Context context, Float maths) {
        super(context);
        this.maths = maths;
        this.selector = 0;
    }

    public Graph(Context context, String maths) {
        super(context);
        this.sMaths = maths;
        this.selector = 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawGraphLines(canvas);
        if(this.selector == 0) {
            drawFunction(canvas, this.maths);
        } else {
            drawFunction(canvas, this.sMaths);
        }


    }

    protected void drawFunction(Canvas canvas, Float maths) {
        Paint paint = new Paint();
        Float y = Math.abs(Float.valueOf(canvas.getHeight()/2-maths*10));
        canvas.drawLine(0, y, Float.valueOf(canvas.getWidth()), y, paint);
    }

    protected void drawFunction(Canvas canvas, String maths) {

    }

    protected void drawGraphLines(Canvas canvas) {
        Float height = Float.valueOf(canvas.getHeight());
        Float width = Float.valueOf(canvas.getWidth());
        Paint paint = new Paint();
        canvas.drawLine(0, height/2, width, height/2, paint);
        canvas.drawLine(width/2, 0, width/2, height, paint);
    }
}
