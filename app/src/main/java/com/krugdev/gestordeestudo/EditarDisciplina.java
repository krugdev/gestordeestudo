package com.krugdev.gestordeestudo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;


public class EditarDisciplina extends Activity {

    private Dados dados;
    private SQLiteDatabase db;
    private EditText disciplina;
    private int posição;


    private static final Integer[] cores = {Color.argb(255,255,0,0), Color.argb(255,0,255,0), Color.argb(255,0,0,255)};

    /*private static final Integer[] emoticons = {R.drawable.cool, R.drawable.amazed, R.drawable.angelic,
            R.drawable.crying, R.drawable.devil, R.drawable.hand,
            R.drawable.laughing, R.drawable.loving, R.drawable.question, R.drawable.sad,
            R.drawable.silence, R.drawable.simple, R.drawable.sleeping, R.drawable.smiling,
            R.drawable.worried};*/


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

        posição=intent.getIntExtra("posição",100);

        String sortOrder = "_id ASC";

        Cursor cursor = db.rawQuery("SELECT * FROM DISCIPLINAS WHERE _id = "+intent.getIntExtra("posição",100),null,null);

        cursor.moveToFirst();

        EditText disciplina = (EditText) findViewById(R.id.editTextDisciplina);
        disciplina.setText(cursor.getString(cursor.getColumnIndex("DISCIPLINA")));

        EditText peso = (EditText) findViewById(R.id.editTextPeso);
        peso.setText(cursor.getString(cursor.getColumnIndex("PESO")));

        EditText tempoTotal = (EditText) findViewById(R.id.editTextTempoTotal);
        tempoTotal.setText(cursor.getString(cursor.getColumnIndex("TEMPO_TOTAL")));

        ImageView cor = (ImageView) findViewById(R.id.imageViewCor);
        cor.setBackgroundColor(cursor.getInt(cursor.getColumnIndex("COR")));

        Spinner corSpinner = (Spinner) findViewById(R.id.corSpinner);

        corSpinner.setAdapter(new MySpinnerAdapter());
        //corSpinner.setOnItemSelectedListener(this);




        cor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), selecionarCor.class);

                intent.putExtra("posição", posição);

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

    private static class ViewHolder {

        ImageView imageViewCor;

    }

    private class MySpinnerAdapter extends BaseAdapter {



        public int getCount() {
            return cores.length;
        }

        @Override
        public Integer getItem(int position) {

            return cores[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View itemView = convertView;
            ViewHolder corViewHolder;

            if (convertView == null) {
//
                itemView = getLayoutInflater()
                        .inflate(R.layout.spinner_row, parent, false);


                corViewHolder = new ViewHolder();
                corViewHolder.imageViewCor
                        = (ImageView) itemView.findViewById(R.id.spinnerImage);

                corViewHolder.imageViewCor.setBackgroundColor(cores[position]);
                itemView.setTag(corViewHolder);


            } else {

                corViewHolder = (ViewHolder) itemView.getTag();

            }


           /* corViewHolder.imageViewCor
                    .setImageDrawable(getResources()
                            .getDrawable(cores[position]));*/

            return itemView;

        }

    }



}
