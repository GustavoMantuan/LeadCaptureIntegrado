package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

/**
 * Created by Dorga on 28/04/2015.
 */
public class ConectaBanco {
    public static final String DATABASE_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String FOLDER_PATH = "external_sd/AgrpIpeDroid/";
    public static final String DATABASE_NAME = "BancoAplicacao.db";

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    private SQLiteDatabase database;

    public ConectaBanco(){
        try {
            database = SQLiteDatabase.openDatabase(DATABASE_FILE_PATH + File.separator + FOLDER_PATH + DATABASE_NAME,null,1);
            setDatabase(database);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
