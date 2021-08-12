package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shopping.sqlite";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE tblshopping (\n" +
                "    id         INTEGER      PRIMARY KEY AUTOINCREMENT,\n" +
                "    name       VARCHAR (50) NOT NULL,\n" +
                "    count      INT          CHECK (count >= 1),\n" +
                "    price      DOUBLE,\n" +
                "    dateBought DATE\n" +
                ");\n";
        sqLiteDatabase.execSQL(sql);
        sql =  "insert into tblshopping(name,count,price,dateBought) VALUES ('Bàn chải',2,8500,'2021-02-15')";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
