package com.krugdev.gestordeestudo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;


public class Grafico extends Activity {

    private Graf Graf;
    private ArrayList<Float> angulo;
    private ArrayList<Integer> cor;
    //private Color conversor;
            //= {10,25,45,60,71,80,98,125,160,230,250,360,360,360,360,360,360,360,360,360};

    //angulo[20] = {10,25,45,60,71,80,98,125,160,230,250,360,360,360,360,360,360,360,360,360};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);
        Graf = (Graf)findViewById(R.id.Graf);

        angulo = new ArrayList<Float>();
        angulo.clear();

        //conversor = new Color();

        cor = new ArrayList<Integer>();
        cor.clear();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grafico, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnPressed(View view) {
        //update the View
        //Graf.setCircleColor(Color.GREEN);
        //Graf.setLabelColor(Color.MAGENTA);
        //Graf.setLabelText("Help");

        angulo.clear();

        angulo.add((float)10);
        angulo.add((float)25);
        angulo.add((float)40);
        angulo.add((float)35);
        angulo.add((float)12);
        angulo.add((float)45);
        angulo.add((float)25);

        cor.clear();

        cor.add(Color.argb(255,255,0,0));
        cor.add(Color.argb(255,255,255,0));
        cor.add(Color.argb(255,255,0,255));
        cor.add(Color.argb(255,0,255,0));
        cor.add(Color.argb(255,0,255,255));
        cor.add(Color.argb(255,255,255,255));
        cor.add(Color.argb(255,0,0,255));

        Graf.set(cor, angulo);
        Graf.invalidate();
        //Graf.requestLayout();
    }
}
