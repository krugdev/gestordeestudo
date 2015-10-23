package com.krugdev.gestordeestudo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class EditarCiclo extends Activity {

    private Dados dados;
    private SQLiteDatabase db;
    private MyCursorAdapter CursorAdapter;
    private AdapterView.OnItemClickListener ItemClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editar_ciclo);

        dados = new Dados(this);
        db = dados.getWritableDatabase();

        EditText tamanhoCiclo = (EditText) findViewById(R.id.editTextTamanho);

        EditText tamanhoBloco = (EditText) findViewById(R.id.editTextBloco);

        SharedPreferences settings = getSharedPreferences("settings",0);

        tamanhoCiclo.setText(Integer.toString(settings.getInt("tamanhoCiclo", 300)));

        tamanhoBloco.setText(Integer.toString(settings.getInt("tamanhoBloco", 300)));

        //Generate ListView from SQLite Database
        displayListView();

    }

    public void salvar(View v){

        Intent intent = new Intent(this, DetalhesCiclo.class);

        EditText tamanhoCiclo = (EditText) findViewById(R.id.editTextTamanho);

        EditText tamanhoBloco = (EditText) findViewById(R.id.editTextBloco);

        SharedPreferences settings = getSharedPreferences("settings",0);

        SharedPreferences.Editor editor = settings.edit();

        editor.putInt("tamanhoCiclo", Integer.parseInt(tamanhoCiclo.getText().toString()));

        editor.putInt("tamanhoBloco", Integer.parseInt(tamanhoBloco.getText().toString()));

        // Commit the edits!
        editor.commit();

        startActivity(intent);


    }


    private void displayListView() {

        String[] colunas = {
                "_id",
                "DISCIPLINA",
                "PESO",
                "COR",
                "TEMPO_TOTAL",
        };

        String sortOrder = "_id ASC";

        Cursor cursor = db.query(
                "DISCIPLINAS",  // The table to query
                colunas,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );


        MyCursorAdapter CursorAdapter = new MyCursorAdapter(this,cursor,0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(CursorAdapter);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editar_ciclo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.adicionar_disciplina:
                Intent intent = new Intent(this, AdicionarDisciplina.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
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
            ImageViewCor.setBackgroundColor(Cor.getCor(cursor.getInt(cursor.getColumnIndex("COR"))));

            //String title = cursor.getString( cursor.getColumnIndex( MyTable.COLUMN_TITLE ) );
            //textViewTitle.setText(title);

            ImageButton editButton = (ImageButton) view.findViewById(R.id.editButton);
            editButton.setTag(cursor.getPosition()+1);

            ImageButton deleteButton = (ImageButton) view.findViewById(R.id.deleteButton);
            deleteButton.setTag(cursor.getPosition()+1);

            editButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(v.getContext(), EditarDisciplina.class);

                    int posição = (int) v.getTag();

                    intent.putExtra("posição", posição);

                    v.getContext().startActivity(intent);

                }
            });


            deleteButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    int posição = (int) v.getTag();

                    

                }
            });


        }

        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            // R.layout.list_row is your xml layout for each row
            return cursorInflater.inflate(R.layout.editar_disciplinas_lista, parent, false);

        }


    }

    /*private void selecionarCor(View v){


        Intent intent = new Intent(v.getContext(), selecionarCor.class);

        intent.putExtra("posição", posição);

        v.getContext().startActivity(intent);


    }*/





}



