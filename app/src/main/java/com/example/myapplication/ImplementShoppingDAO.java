package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImplementShoppingDAO implements IShoppingDAO{
    private Context context;
    private SQLiteDatabase db;

    public ImplementShoppingDAO(Context context) {
        this.context = context;
        DatabaseHelper helper = new DatabaseHelper(this.context);
        db = helper.getWritableDatabase();
    }

    @Override
    public boolean insert(Shopping s) {
        ContentValues content = new ContentValues();
        content.put("name",s.getName());
        content.put("count",s.getCount());
        content.put("price",s.getPrice());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        content.put("dateBought",fmt.format(s.getDateBought()));

        long idItem = db.insert("tblshopping", null, content);
        if (idItem > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Shopping> selectAll() {
        String sql = "select * from tblshopping";
        Cursor c = db.rawQuery(sql, null);
        List<Shopping> list = new ArrayList<>();
        int numberId = c.getColumnIndex("id");
        int numberName = c.getColumnIndex("name");
        int numberCount = c.getColumnIndex("count");
        int numberPrice = c.getColumnIndex("price");
        int numberDate = c.getColumnIndex("dateBought");
        while (c.moveToNext()){
            try {
             int id = c.getInt(numberId);
             String name = c.getString(numberName);
             int count = c.getInt(numberCount);
             double price = c.getDouble(numberPrice);
             SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
             Date dateBought = fmt.parse(c.getString(numberDate));
             Shopping s = new Shopping(id,name,count,price,dateBought);
             list.add(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Shopping selectById(int id) {
        String sql = "select * from tblshopping where id = ?";
        String strArgs[] = {String.valueOf(id)};
        Cursor c = db.rawQuery(sql,strArgs);
        int numberId = c.getColumnIndex("id");
        int numberName = c.getColumnIndex("name");
        int numberCount = c.getColumnIndex("count");
        int numberPrice = c.getColumnIndex("price");
        int numberDate = c.getColumnIndex("dateBought");
        while (c.moveToNext()){
            try {
                int idS = c.getInt(numberId);
                String name = c.getString(numberName);
                int count = c.getInt(numberCount);
                double price = c.getDouble(numberPrice);
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                Date dateBought = fmt.parse(c.getString(numberDate));
                Shopping s = new Shopping(idS,name,count,price,dateBought);
                return  s;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean update(int id, Shopping s) {
        ContentValues content = new ContentValues();
        content.put("name",s.getName());
        content.put("count",s.getCount());
        content.put("price",s.getPrice());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        content.put("dateBought",fmt.format(s.getDateBought()));

        long idItem = db.update("tblshopping", content,"id=?",new String[]{String.valueOf(id)});
        if (idItem > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        int isOk = db.delete("tblshopping", "id=?", new String[]{String.valueOf(id)});
        if (isOk > 0){
            return true;
        }
        return false;
    }
}
