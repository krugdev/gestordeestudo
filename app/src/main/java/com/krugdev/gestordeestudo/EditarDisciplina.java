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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


public class EditarDisciplina extends Activity implements AdapterView.OnItemSelectedListener {

    private Dados dados;
    private SQLiteDatabase db;
    private EditText disciplina;
    private int posição;


   // private static final Integer[] cores = {Color.argb(255,255,0,0), Color.argb(255,0,255,0), Color.argb(255,0,0,255)};

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

        ImageButton salvar = (ImageButton) findViewById(R.id.salvar);

        //ImageView cor = (ImageView) findViewById(R.id.imageViewCor);
        //cor.setBackgroundColor(Cor.getCor(cursor.getInt(cursor.getColumnIndex("COR"))));

        final Spinner corSpinner = (Spinner) findViewById(R.id.corSpinner);
        corSpinner.setAdapter(new MySpinnerAdapter());
        corSpinner.setOnItemSelectedListener(this);
        corSpinner.setSelection(cursor.getInt(cursor.getColumnIndex("COR")));

        /*salvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), EditarDisciplina.class);

                int posição = (int) v.getTag();

                intent.putExtra("posição", posição);

                v.getContext().startActivity(intent);

                db.execSQL("UPDATE DISCIPLINAS SET DISCIPLINA='"+disciplina.toString()+"',PESO='"+peso.toString()+"',TEMPO_TOTAL="+tempoTotal.toString()+"',COR="+corSpinner.getSelectedItemPosition()+"'WHERE _id='"+intent.getIntExtra("posição",100));
               // UPDATE COMPANY SET ADDRESS = 'Texas' ,  WHERE ID = 6
               // intent.putExtra("posição", corSpinner.getSelectedItemPosition());


            }
        });*/


        //corSpinner.getSelectedItemPosition();


    }

    public void salvar(View v){

        EditText disciplina = (EditText) findViewById(R.id.editTextDisciplina);

        EditText peso = (EditText) findViewById(R.id.editTextPeso);

        EditText tempoTotal = (EditText) findViewById(R.id.editTextTempoTotal);

        Spinner corSpinner = (Spinner) findViewById(R.id.corSpinner);

        //Intent intent = new Intent(v.getContext(), EditarDisciplina.class);

        //int posição = (int) v.getTag();

        //intent.putExtra("posição", posição);

        //v.getContext().startActivity(intent);

        db.execSQL("UPDATE DISCIPLINAS SET DISCIPLINA='"+disciplina.getText().toString()+"',PESO="+peso.getText().toString()+",TEMPO_TOTAL="+tempoTotal.getText().toString()+",COR="+corSpinner.getSelectedItemPosition()+" WHERE _id="+posição+";");

        // UPDATE COMPANY SET ADDRESS = 'Texas' ,  WHERE ID = 6
        // intent.putExtra("posição", corSpinner.getSelectedItemPosition());

        Intent intent = new Intent(this, EditarCiclo.class);
        startActivity(intent);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent,
                               View view, int position, long id) {
        Toast.makeText(this, "Selected: "
                + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
//        nothing selected
    }


    /*
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
    }*/

    private static class ViewHolder {

        ImageView imageViewCor;

    }

    private class MySpinnerAdapter extends BaseAdapter {



        public int getCount() {
            return Cor.qtd();
        }

        @Override
        public Integer getItem(int position) {

            return Cor.getCor(position);
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

                corViewHolder.imageViewCor.setBackgroundColor(Cor.getCor(position));
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
