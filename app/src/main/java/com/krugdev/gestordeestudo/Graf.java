package com.krugdev.gestordeestudo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Krug on 24/08/2015.
 */
public class Graf extends View {

    //circle and text colors
    private int circleCol, labelCol;

    //label text
    private String circleText;

    //paint for drawing custom view
    private Paint ArcPaint;

    private ArrayList angulo;

    private ArrayList cor;

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
        ArcPaint.setStyle(Style.STROKE);
        ArcPaint.setAntiAlias(true);
        ArcPaint.setStrokeWidth(10);
        angulo = new ArrayList();
        angulo.clear();
        cor = new ArrayList();
        cor.clear();


        /*for(int i=0 ; i < qtd ; i++){
            Arc[i] = new ArcShape(angulo[i],angulo[i+1]);
            Arc[i].s
            Shape[i] = new ShapeDrawable(Arc[i]);
            ArcPaint[i] = new Paint();
            ArcPaint[i].setStyle(Style.FILL);
            ArcPaint[i].setAntiAlias(true);
            ArcPaint[i].setColor(cor[i]);
            ArcPaint[i].setStrokeWidth(10);
            Shape[i].getPaint().set(ArcPaint[i]);
        }*/


    }


    @Override
    protected void onDraw(Canvas canvas) {
        //draw the View

        //get half of the width and height as we are working with a circle
        int viewWidthHalf = this.getMeasuredWidth()/2;
        int viewHeightHalf = this.getMeasuredHeight()/2;

        //get the radius as half of the width or height, whichever is smaller
        //subtract ten so that it has some space around it
        int radius = 0;
        if(viewWidthHalf>viewHeightHalf)
            radius=viewHeightHalf-10;
        else
            radius=viewWidthHalf-10;

        int anguloInicial = 0;

        for(int i=0 ; i < angulo.size()  ; i++){
            ArcPaint.setColor((int) cor.get(i));
            canvas.drawArc(10, 10, radius * 2, radius * 2, anguloInicial, (float) angulo.get(i), false, ArcPaint);
        }


    }


    public void set(ArrayList c,ArrayList a){
        cor.clear();
        angulo.clear();
        cor = c;
        angulo = a;
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
