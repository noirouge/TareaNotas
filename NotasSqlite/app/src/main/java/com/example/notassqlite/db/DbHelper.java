package com.example.notassqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

private static final int DATABASE_VERSION = 1;
private static final String DATABASE_NAME = "notas.db";
public static  final String TABLE_NOTAS = "t_notas";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NOTAS + "("+
        "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
        "title TEXT,"+
        "description TEXT,"+
        "deleted INTEGER DEFAULT 0)"
);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_NOTAS);
onCreate(sqLiteDatabase);

    }




}
