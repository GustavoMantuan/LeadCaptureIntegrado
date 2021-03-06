package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.dao;


import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorga on 28/04/2015.
 */
public abstract class Connection<T> implements DAO<T> {
    private static final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String SN_PATH = "/storage/sdcard1";
    private static final String DB_NAME = "BaseLeads.db";

    private SQLiteDatabase database;

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public Connection(){
        try {
            database = SQLiteDatabase.openDatabase(SD_PATH + File.separator +  DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
