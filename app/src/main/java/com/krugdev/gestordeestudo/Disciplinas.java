package com.krugdev.gestordeestudo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Disciplinas extends Activity {


    private Dados dados;
    private SQLiteDatabase db;
    //private SimpleCursorAdapter dataAdapter;
    private MyCursorAdapter CursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplinas);

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

        /*
        // The desired columns to be bound
        String[] columns = new String[] {
                "DISCIPLINA",
                "PESO",
                "TEMPO_TOTAL",
                "COR"
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.disciplina,
                R.id.peso,
                R.id.tempoTotal,
                R.id.cor,
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.disciplinas,
                cursor,
                columns,
                to,
                0);

        */

        MyCursorAdapter CursorAdapter = new MyCursorAdapter(this,cursor,0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(CursorAdapter);


        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // Get the state's capital from this row in the database.
                String toast =
                        cursor.getString(cursor.getColumnIndexOrThrow("DISCIPLINA"));
                Toast.makeText(getApplicationContext(),
                        toast, Toast.LENGTH_SHORT).show();

            }
        });
        */


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_disciplinas, menu);
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



    public void editarDisciplina(View view) {
        Intent intent = new Intent(this, EditarDisciplina.class);
        startActivity(intent);
    }





}
