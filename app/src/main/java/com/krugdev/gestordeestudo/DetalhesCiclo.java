package com.krugdev.gestordeestudo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DetalhesCiclo extends Activity {


    private Dados dados;
    private SQLiteDatabase db;
    private MyCursorAdapter CursorAdapter;
    private AdapterView.OnItemClickListener ItemClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo);

        dados = new Dados(this);
        db = dados.getWritableDatabase();

        //Generate ListView from SQLite Database
        displayListView();


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
        getMenuInflater().inflate(R.menu.menu_detalhes_ciclo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editar_ciclo:
                Intent intent = new Intent(this, EditarCiclo.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }




    private class MyCursorAdapter extends android.widget.CursorAdapter {

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

            ImageView ImageViewCor = (ImageView) view.findViewById(R.id.imageViewCor);
            ImageViewCor.setBackgroundColor(Cor.getCor(cursor.getInt(cursor.getColumnIndex("COR"))));


        }

        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            // R.layout.list_row is your xml layout for each row
            return cursorInflater.inflate(R.layout.disciplinas_lista, parent, false);

        }


    }






}
