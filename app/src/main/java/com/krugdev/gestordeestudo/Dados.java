package com.krugdev.gestordeestudo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class Dados extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Dados.db";
    private static final String SQL_CREATE_TABLE_DISCIPLINAS = "CREATE TABLE DISCIPLINAS(_id INTEGER PRIMARY KEY AUTOINCREMENT, DISCIPLINA TEXT NOT NULL, PESO INTEGER NOT NULL, COR INTEGER NOT NULL, TEMPO_TOTAL INTEGER NOT NULL);";
    private static final String SQL_CREATE_TABLE_CICLO = "CREATE TABLE CICLO(_id INTEGER PRIMARY KEY AUTOINCREMENT, DISCIPLINA_ID INTEGER NOT NULL, TEMPO_INIT INTEGER NOT NULL, DURACAO INTEGER NOT NULL);";
    private static final String SQL_CREATE_TABLE_REGISTROS = "CREATE TABLE REGISTROS(_id INTEGER PRIMARY KEY AUTOINCREMENT, BLOCO_ID INTEGER NOT NULL, DATA TEXT NOT NULL, DURACAO INTEGER NOT NULL);";

    private static final String SQL_DELETE_DISCIPLINAS = "DROP TABLE IF EXISTS DISCIPLINAS;";
    private static final String SQL_DELETE_REGISTROS = "DROP TABLE IF EXISTS REGISTROS;";
    private static final String SQL_DELETE_CICLO = "DROP TABLE IF EXISTS CICLO;";

    public Dados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_DISCIPLINAS);
        db.execSQL(SQL_CREATE_TABLE_CICLO);
        db.execSQL(SQL_CREATE_TABLE_REGISTROS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }








}
