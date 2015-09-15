package com.krugdev.gestordeestudo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditarDisciplina extends Activity {

    private Dados dados;
    private SQLiteDatabase db;
    private EditText disciplina;


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
        button.setTag(intent.getIntExtra("posição", 100));


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


        return super.onOptionsItemSelected(item);
    }


    private class MyCursorAdapter extends CursorAdapter {

        private LayoutInflater cursorInflater;
        final private Context context = this.context;

        // Default constructor
        public MyCursorAdapter(Context context, Cursor cursor, int flags) {
            super(context, cursor, flags);
            cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void bindView(View view, Context context, Cursor cursor) {
            TextView disciplina = (TextView) view.findViewById(R.id.disciplina);
            disciplina.setText(cursor.getString(cursor.getColumnIndex("DISCIPLINA")));

            TextView peso = (TextView) view.findViewById(R.id.peso);
            peso.setText(cursor.getString(cursor.getColumnIndex("PESO")));

            TextView tempoTotal = (TextView) view.findViewById(R.id.tempoTotal);
            tempoTotal.setText(cursor.getString(cursor.getColumnIndex("TEMPO_TOTAL")));

            //TextView cor = (TextView) view.findViewById(R.id.cor);
            //cor.setText(cursor.getString(cursor.getColumnIndex("COR")));

            ImageView ImageViewCor = (ImageView) view.findViewById(R.id.imageViewCor);
            ImageViewCor.setBackgroundColor(cursor.getInt(cursor.getColumnIndex("COR")));

            //String title = cursor.getString( cursor.getColumnIndex( MyTable.COLUMN_TITLE ) );
            //textViewTitle.setText(title);

            ImageButton button = (ImageButton) view.findViewById(R.id.editButton);
            button.setTag(cursor.getPosition()+1);

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(v.getContext(), EditarDisciplina.class);

                    int posição = (int) v.getTag();

                    intent.putExtra("posição", posição);

                    v.getContext().startActivity(intent);

                }
            });


        }

        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            // R.layout.list_row is your xml layout for each row
            return cursorInflater.inflate(R.layout.editar_disciplinas_lista, parent, false);

        }


    }









}



