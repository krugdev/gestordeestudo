package com.krugdev.gestordeestudo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Krug on 03/09/2015.
 */
public class MyCursorAdapter extends CursorAdapter {

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
        button.setTag(cursor.getPosition());

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
        return cursorInflater.inflate(R.layout.disciplinas, parent, false);

    }


}
