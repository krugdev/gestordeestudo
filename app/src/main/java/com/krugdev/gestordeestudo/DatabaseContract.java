package com.krugdev.gestordeestudo;

import android.provider.BaseColumns;

/**
 * Created by Krug on 28/08/2015.
 */
public final class DatabaseContract {

    public DatabaseContract() {}

    /* Inner class that defines the table contents */
    public static abstract class TabelaDisciplinas implements BaseColumns {
        public static final String TABLE_NAME = "disciplinas";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_DISCIPLINA = "disciplina";
        public static final String COLUMN_NAME_PESO = "peso";
        public static final String COLUMN_NAME_COR = "cor";
        public static final String COLUMN_NAME_TEMPO_TOTAL = "tempo_total";
    }

    public static abstract class TabelaCiclo implements BaseColumns {
        public static final String TABLE_NAME = "ciclo";
        public static final String COLUMN_NAME_BLOCO = "bloco";
        public static final String COLUMN_NAME_DISCIPLINA = "disciplina";
        public static final String COLUMN_NAME_TEMPO_INIT = "tempo_inicial";
        public static final String COLUMN_NAME_DURAÇÃO = "duração";
    }

    public static abstract class TabelaRegistros implements BaseColumns {
        public static final String TABLE_NAME = "registros";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_BLOCO = "bloco";
        public static final String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_DURAÇÃO = "duração";
    }

    







}
