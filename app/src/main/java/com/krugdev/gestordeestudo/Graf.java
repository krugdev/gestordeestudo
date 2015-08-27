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

    //circle and text colors
    private int circleCol, labelCol;

    //label text
    private String circleText;

    //paint for drawing custom view
    private Paint ArcPaint;

    private ArrayList<Float> angulo;

    private ArrayList<Integer> cor;

    public Graf(Context context, AttributeSet attrs){
        super(context, attrs);

        //paint object for drawing in onDraw

        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Graf, 0, 0);

        try {
            //get the text and colors specified using the names in attrs.xml
            circleText = a.getString(R.styleable.Graf_circleLabel);
            circleCol = a.getInteger(R.styleable.Graf_circleColor, 0);//0 is default
            labelCol = a.getInteger(R.styleable.Graf_labelColor, 0);

        } finally {
            a.recycle();
        }

        ArcPaint = new Paint();
        angulo = new ArrayList<Float>();
        cor = new ArrayList<Integer>();



    }


    @Override
    protected void onDraw(Canvas canvas) {
        //draw the View

        //get half of the width and height as we are working with a circle
        //int viewWidthHalf = this.getMeasuredWidth()/2;
        //int viewHeightHalf = this.getMeasuredHeight()/2;

        //get the radius as half of the width or height, whichever is smaller
        //subtract ten so that it has some space around it

        ArcPaint.setStyle(Style.STROKE);
        ArcPaint.setAntiAlias(true);
        int espessura = 200;
        ArcPaint.setStrokeWidth(espessura);


        if(this.getMeasuredWidth()<this.getMeasuredHeight()) {
            //int size = 0;
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

    public int getCircleColor(){
        return circleCol;
    }

    public int getLabelColor(){
        return labelCol;
    }

    public String getLabelText(){
        return circleText;
    }

    public void setCircleColor(int newColor){
        //update the instance variable
        circleCol=newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }
    public void setLabelColor(int newColor){
        //update the instance variable
        labelCol=newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public void setLabelText(String newLabel){
        //update the instance variable
        circleText=newLabel;
        //redraw the view
        invalidate();
        requestLayout();
    }





}
