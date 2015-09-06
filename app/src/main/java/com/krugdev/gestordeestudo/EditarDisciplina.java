package com.krugdev.gestordeestudo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditarDisciplina extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editar_disciplina);

        Intent intent = getIntent();

        Toast.makeText(this,"YEAH "+intent.getIntExtra("posição",100), Toast.LENGTH_LONG).show();

        /*EditText disciplina = (EditText) findViewById(R.id.editTextDisciplina);
        disciplina.setText(intent.getStringExtra("posição"));

        EditText peso = (EditText) findViewById(R.id.editTextPeso);
        peso.setText(intent.getStringExtra("peso"));

        EditText tempoTotal = (EditText) findViewById(R.id.editTextTempoTotal);
        tempoTotal.setText(intent.getStringExtra("peso"));

        ImageView cor = (ImageView) findViewById(R.id.imageViewCor);
        cor.setBackgroundColor(intent.getIntExtra("cor", 0));*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editar_disciplina, menu);
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
}
