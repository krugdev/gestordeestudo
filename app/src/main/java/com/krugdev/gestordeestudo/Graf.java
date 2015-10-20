package com.krugdev.gestordeestudo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.lang.Math;



public class Graf extends View {

    private Paint ArcPaint;

    private ArrayList<Float> angulo;

    private ArrayList<Integer> cor;

    public Graf(Context context, AttributeSet attrs){
        super(context);

        ArcPaint = new Paint();
        angulo = new ArrayList<Float>();
        cor = new ArrayList<Integer>();



    }


    @Override
    protected void onDraw(Canvas canvas) {


        ArcPaint.setStyle(Style.STROKE);
        ArcPaint.setAntiAlias(true);
        int espessura = 200;
        ArcPaint.setStrokeWidth(espessura);
        ArcPaint.getStrokeCap();


        if(this.getMeasuredWidth()<this.getMeasuredHeight()) {

            float anguloInicial = -90;
            for (int i = 0; i < angulo.size(); i++) {
                ArcPaint.setColor(cor.get(i));
                canvas.drawArc(espessura/2, (this.getMeasuredHeight() - this.getMeasuredWidth()) / 2 + espessura/2, this.getMeasuredWidth()-espessura/2, this.getMeasuredWidth() + (this.getMeasuredHeight() - this.getMeasuredWidth()) / 2 - espessura/2, anguloInicial, angulo.get(i), false, ArcPaint);
                anguloInicial = anguloInicial + angulo.get(i);
            }
        }
        else {

            float anguloInicial = -90;
            for (int i = 0; i < angulo.size(); i++) {
                ArcPaint.setColor(cor.get(i));
                canvas.drawArc((this.getMeasuredWidth() - this.getMeasuredHeight()) / 2 + espessura/2 , espessura/2 , this.getMeasuredWidth()-(this.getMeasuredWidth() - this.getMeasuredHeight()) / 2 - espessura/2, this.getMeasuredHeight()-espessura/2, anguloInicial, angulo.get(i), false, ArcPaint);
                anguloInicial = anguloInicial + angulo.get(i);
            }

        }

    }


    public void set(ArrayList<Integer> c,ArrayList<Float> a){

        cor.clear();
        angulo.clear();
        cor.addAll(c);
        angulo.addAll(a);
        invalidate();
        requestLayout();
    }



}
