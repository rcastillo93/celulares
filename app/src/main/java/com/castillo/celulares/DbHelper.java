package com.castillo.celulares;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by René Castillo on 17/10/2017.
 */

public class DbHelper extends SQLiteOpenHelper{
    String crear = "create table categorias(id integer primary key autoincrement, categoria text not null);";
    public DbHelper(Context context) {
        super(context,"inventario.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(crear);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table categorias");
        db.execSQL(crear);

    }



}
