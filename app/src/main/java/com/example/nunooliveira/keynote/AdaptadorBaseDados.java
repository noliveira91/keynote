package com.example.nunooliveira.keynote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nuno Oliveira on 30/12/2017.
 */

public class AdaptadorBaseDados {

    private AjudaUsoBaseDados dbHelper;
    private SQLiteDatabase database;

    public AdaptadorBaseDados(Context context) {
        dbHelper = new AjudaUsoBaseDados(context);
    }

    public AdaptadorBaseDados open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public Cursor obterRegistos(){
        Cursor cursor = database.rawQuery(
                "select _id, aplicacao, utilizador, email, password from registos", null);
        return cursor;
    }

    public Cursor obterRegistosId(Integer id){
        Cursor cursor = database.rawQuery(
                "select _id, aplicacao, utilizador, email, password from registos where _id=?", new String[] { id.toString() });
        return cursor;
    }

    public boolean insereRegisto(String aplicacao, String utilizador, String email, String password){
        ContentValues valores;
        long resultado;

        database = dbHelper.getWritableDatabase();
        valores = new ContentValues();
        valores.put("aplicacao", aplicacao);
        valores.put("utilizador", utilizador);
        valores.put("email", email);
        valores.put("password", password);

        resultado = database.insert("registos", null, valores);
        database.close();

        if (resultado ==-1)
            return false;
        else
            return true;

    }

    public boolean alteraRegisto(Integer id, String aplicacao, String utilizador, String email, String password) {
        long resultado;
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new Integer(id).toString();
        ContentValues values = new ContentValues();
        values.put("aplicacao", aplicacao);
        values.put("utilizador", utilizador);
        values.put("email", email);
        values.put("password", password);
        resultado =  database.update("registos", values, whereClause, whereArgs);
        if (resultado ==-1)
            return false;
        else
            return true;
    }

    public int apagarRegisto(Integer id) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = ""+id;
        return database.delete("registos", whereClause, whereArgs);
    }
}
