package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dorga on 02/04/2015.
 */
public class DbConnect extends SQLiteOpenHelper {
    private static final String BD = "APP";
    private static final int VERSAO = 1;
    public Resources res;

    public DbConnect(Context context) {
        super(context, BD, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Cidade (id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL, nome STRING (30) UNIQUE, estado STRING (2)); ");
        db.execSQL("CREATE TABLE Curso (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome STRING (30), aberto INTEGER);");
        db.execSQL("CREATE TABLE Evento (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome STRING (30), data DATE);");
        db.execSQL("CREATE TABLE Lead (" +
                "    id          INTEGER      PRIMARY KEY AUTOINCREMENT," +
                "    nome        STRING (50)," +
                "    email       STRING (40)," +
                "    telefoneRes STRING (30)," +
                "    telefoneCel STRING," +
                "    nascimento  DATE," +
                "    endereco    STRING (50)," +
                "    numero      STRING (10)," +
                "    bairro      STRING (35)," +
                "    fies        INTEGER," +
                "    prouni      INTEGER," +
                "    percProuni  DOUBLE," +
                "    face        NUMERIC (50)," +
                "    enen        INTEGER," +
                "    notaEnen    DOUBLE," +
                "    dataLead    DATE," +
                "    idCidade    REFERENCES Cidade (id)," +
                "    idCurso1                 REFERENCES Curso (id)," +
                "    idCurso2                 REFERENCES Curso (id)," +
                "    idEvento                 REFERENCES Evento (id) " +
                ");");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
