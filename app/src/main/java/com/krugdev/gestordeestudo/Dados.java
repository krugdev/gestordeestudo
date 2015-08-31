package com.krugdev.gestordeestudo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class Dados extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Dados.db";
    private static final String SQL_CREATE_TABLE_DISCIPLINAS = "CREATE TABLE DISCIPLINAS( _id INT PRIMARY KEY NOT NULL, DISCIPLINA TEXT NOT NULL, PESO REAL NOT NULL, COR CHAR(9) NOT NULL, TEMPO_TOTAL INT NOT NULL);";
    private static final String SQL_CREATE_TABLE_CICLO = "CREATE TABLE CICLO( _id INT PRIMARY KEY NOT NULL, DISCIPLINA_ID INT NOT NULL, TEMPO_INIT INT NOT NULL, DURACAO INT NOT NULL);";
    private static final String SQL_CREATE_TABLE_REGISTROS = "CREATE TABLE REGISTROS( _id INT PRIMARY KEY NOT NULL, BLOCO_ID INT NOT NULL, DATA TEXT NOT NULL, DURACAO INT NOT NULL);";

    private static final String SQL_DELETE_DISCIPLINAS = "DROP TABLE IF EXISTS DISCIPLINAS;";
    private static final String SQL_DELETE_REGISTROS = "DROP TABLE IF EXISTS REGISTROS;";
    private static final String SQL_DELETE_CICLO = "DROP TABLE IF EXISTS CICLO;";

    public Dados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_DISCIPLINAS);
        db.execSQL(SQL_CREATE_TABLE_CICLO);
        db.execSQL(SQL_CREATE_TABLE_REGISTROS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_DISCIPLINAS);
        db.execSQL(SQL_DELETE_REGISTROS);
        db.execSQL(SQL_DELETE_CICLO);

        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }



}
