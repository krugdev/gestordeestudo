package com.krugdev.gestordeestudo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class EditarDisciplina extends Activity {

    private Dados dados;
    private SQLiteDatabase db;
    private EditText disciplina;
    //private MyCursorAdapter CursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editar_disciplina);

        Intent intent = getIntent();

        dados = new Dados(this);

        db = dados.getWritableDatabase();

        String[] colunas = {
                "_id",
                "DISCIPLINA",
                "PESO",
                "COR",
                "TEMPO_TOTAL",
        };

        String[] where = {
                "_id="+intent.getIntExtra("posição",100)
        };

        String sortOrder = "_id ASC";

        Cursor cursor = db.rawQuery("SELECT * FROM DISCIPLINAS WHERE _id = "+intent.getIntExtra("posição",100),null,null);




        /*Cursor cursor = db.query(
                "DISCIPLINAS",  // The table to query
                colunas,                               // The columns to return
                null,                                // The columns for the WHERE clause
                where,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );*/

        cursor.moveToFirst();

        //Toast.makeText(this,"YEAH "+intent.getIntExtra("posição",100), Toast.LENGTH_LONG).show();
        Toast.makeText(this,"YEAH "+cursor.getString(cursor.getColumnIndex("DISCIPLINA")), Toast.LENGTH_LONG).show();

        EditText disciplina = (EditText) findViewById(R.id.editTextDisciplina);
        disciplina.setText(cursor.getString(cursor.getColumnIndex("DISCIPLINA")));

        EditText peso = (EditText) findViewById(R.id.editTextPeso);
        peso.setText(cursor.getString(cursor.getColumnIndex("PESO")));

        EditText tempoTotal = (EditText) findViewById(R.id.editTextTempoTotal);
        tempoTotal.setText(cursor.getString(cursor.getColumnIndex("TEMPO_TOTAL")));

        ImageView cor = (ImageView) findViewById(R.id.imageViewCor);
        cor.setBackgroundColor(cursor.getInt(cursor.getColumnIndex("COR")));

        ImageButton button = (ImageButton) findViewById(R.id.editButton);
        button.setTag(intent.getIntExtra("posição",100));


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), EditarCiclo.class);
                EditText disciplina = (EditText) findViewById(R.id.editTextDisciplina);
                EditText peso = (EditText) findViewById(R.id.editTextPeso);
                EditText tempoTotal = (EditText) findViewById(R.id.editTextTempoTotal);
                ImageView cor = (ImageView) findViewById(R.id.imageViewCor);
                db.execSQL("UPDATE DISCIPLINAS SET DISCIPLINA = '" + disciplina.getText().toString() + "' , PESO = " + peso.getText().toString() + " , TEMPO_TOTAL = " + tempoTotal.getText().toString() + " , COR = "+ cor.getSolidColor() + " WHERE _id = " +v.getTag());
                v.getContext().startActivity(intent);

            }
        });





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

    public void salvar(){


    }
}
