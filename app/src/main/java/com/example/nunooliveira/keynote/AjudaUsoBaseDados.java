package com.example.nunooliveira.keynote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nuno Oliveira on 30/12/2017.
 */

public class AjudaUsoBaseDados extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "base-dados.db";
    private static final int VERSION = 1;

    public AjudaUsoBaseDados(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String registos = "CREATE TABLE registos(_id integer primary key autoincrement, aplicacao varchar(40), utilizador varchar(40), email varchar(40), password varchar(40))";
        db.execSQL(registos);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS registos");
        onCreate(db);
    }
}
