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
    private ArrayList angulo;
    private ArrayList cor;
    private Color conversor;
            //= {10,25,45,60,71,80,98,125,160,230,250,360,360,360,360,360,360,360,360,360};

    //angulo[20] = {10,25,45,60,71,80,98,125,160,230,250,360,360,360,360,360,360,360,360,360};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);
        Graf = (Graf)findViewById(R.id.Graf);

        angulo = new ArrayList();
        angulo.clear();
        angulo.add(10);
        angulo.add(25);
        angulo.add(40);
        angulo.add(35);
        angulo.add(12);
        angulo.add(45);
        angulo.add(25);

        conversor = new Color();

        cor = new ArrayList();
        cor.clear();
        cor.add(conversor.argb(0,255,0,0));
        cor.add(conversor.argb(0,255,255,0));
        cor.add(conversor.argb(0,255,0,255));
        cor.add(conversor.argb(0,0,255,0));
        cor.add(conversor.argb(0,0,255,255));
        cor.add(conversor.argb(0,255,255,255));
        cor.add(conversor.argb(0,0,0,255));

        Graf.set(cor,angulo);

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
        Graf.setCircleColor(Color.GREEN);
        Graf.setLabelColor(Color.MAGENTA);
        Graf.setLabelText("Help");
        Graf.set();
        Graf.invalidate();
    }
}
