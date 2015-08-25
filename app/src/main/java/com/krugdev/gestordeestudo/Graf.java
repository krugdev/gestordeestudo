package com.krugdev.gestordeestudo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.util.AttributeSet;
import android.view.View;

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

    private ArcShape Arc[];

    private int qtd;

    private int cor[];

    private ShapeDrawable Shape;



    public Graf(Context context, AttributeSet attrs){
        super(context, attrs);

        //paint object for drawing in onDraw
        ArcPaint = new Paint();

        Shape = new ShapeDrawable();

        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Graf, 0, 0);

        try {
            //get the text and colors specified using the names in attrs.xml
            circleText = a.getString(R.styleable.Graf_circleLabel);
            circleCol = a.getInteger(R.styleable.Graf_circleColor, 0);//0 is default
            labelCol = a.getInteger(R.styleable.Graf_labelColor, 0);
            cor[0]=a.getInteger(R.styleable.Graf_sliceColor_0, 0);
            cor[1]=a.getInteger(R.styleable.Graf_sliceColor_1, 0);
            cor[2]=a.getInteger(R.styleable.Graf_sliceColor_2, 0);
            cor[3]=a.getInteger(R.styleable.Graf_sliceColor_3, 0);
            cor[4]=a.getInteger(R.styleable.Graf_sliceColor_4, 0);
            cor[5]=a.getInteger(R.styleable.Graf_sliceColor_5, 0);
            cor[6]=a.getInteger(R.styleable.Graf_sliceColor_6, 0);
            cor[7]=a.getInteger(R.styleable.Graf_sliceColor_7, 0);
            cor[8]=a.getInteger(R.styleable.Graf_sliceColor_8, 0);
            cor[9]=a.getInteger(R.styleable.Graf_sliceColor_9, 0);
            cor[10]=a.getInteger(R.styleable.Graf_sliceColor_10, 0);
            cor[11]=a.getInteger(R.styleable.Graf_sliceColor_11, 0);
            cor[12]=a.getInteger(R.styleable.Graf_sliceColor_12, 0);
            cor[13]=a.getInteger(R.styleable.Graf_sliceColor_13, 0);
            cor[14]=a.getInteger(R.styleable.Graf_sliceColor_14, 0);
            cor[15]=a.getInteger(R.styleable.Graf_sliceColor_15, 0);
            cor[16]=a.getInteger(R.styleable.Graf_sliceColor_16, 0);
            cor[17]=a.getInteger(R.styleable.Graf_sliceColor_17, 0);
            cor[18]=a.getInteger(R.styleable.Graf_sliceColor_18, 0);
            cor[19]=a.getInteger(R.styleable.Graf_sliceColor_19, 0);


        } finally {
            a.recycle();
        }




    }

    public void angulo(int a[]) {

        for(int i=0 ; i < 20 ; i++){
            Arc[i] = new ArcShape(a[i],a[i+1]);
        }

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

        ArcPaint.setStyle(Style.FILL);
        ArcPaint.setAntiAlias(true);

        //set the paint color using the circle color specified
        ArcPaint.setColor(circleCol);


        Shape.setBounds(10, 10, radius * 2, radius * 2);



        for(int i=0 ; i < 20 ; i++){
            Shape.setShape(Arc[i]);
            ArcPaint.setColor(cor[i]);
            Shape.getPaint().set(ArcPaint);
            Shape.draw(canvas);
        }



        //canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);

        //set the text color using the color specified
        //ArcPaint.setColor(labelCol);

        //set text properties
        //ArcPaint.setTextAlign(Paint.Align.CENTER);
        //ArcPaint.setTextSize(50);

        //draw the text using the string attribute and chosen properties
        //canvas.drawText(circleText, viewWidthHalf, viewHeightHalf, circlePaint);


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
