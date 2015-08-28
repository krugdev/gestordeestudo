package com.krugdev.gestordeestudo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class Grafico extends Activity {

    private Graf Graf;
    private ArrayList<Float> angulo;
    private ArrayList<Integer> cor;

    private TextView textTimer;
    private Button startButton;
    private Button resetButton;
    private long startTime = 0L;
    private Handler myHandler = new Handler();
    long timeInMillies = 0L;
    long timeSwap = 0L;
    long finalTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        Graf = (Graf)findViewById(R.id.Graf);

        angulo = new ArrayList<Float>();
        angulo.clear();

        cor = new ArrayList<Integer>();
        cor.clear();

        textTimer = (TextView) findViewById(R.id.textTimer);

        startButton = (Button) findViewById(R.id.btnStart);
        startButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if(startButton.getText().toString().equals("Start"))
                {
                    startTime = SystemClock.uptimeMillis();
                    myHandler.postDelayed(updateTimerMethod, 1000);
                    startButton.setText("Pause");
                }
                else{
                    timeSwap += timeInMillies;
                    myHandler.removeCallbacks(updateTimerMethod);
                    startButton.setText("Start");
                }




            }
        });

        resetButton = (Button) findViewById(R.id.btnReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myHandler.removeCallbacks(updateTimerMethod);
                timeSwap=0;
                textTimer.setText("00:00:00");
                startButton.setText("Start");
            }
        });


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

    }

    private Runnable updateTimerMethod = new Runnable() {

        public void run() {
            timeInMillies = SystemClock.uptimeMillis()-startTime;
            finalTime = timeSwap + timeInMillies;

            int seconds = (int) (finalTime / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            seconds = seconds % 60;
            hours = hours % 60;
            //int milliseconds = (int) (finalTime % 1000);
            textTimer.setText("" +String.format("%02d",hours) +":"
                    +String.format("%02d",minutes)+":"
                    +String.format("%02d",seconds));
            myHandler.postDelayed(this, 1000);
        }

    };


}
