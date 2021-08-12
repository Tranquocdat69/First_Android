package com.example.myapplication;

import java.util.List;

public interface IShoppingDAO {
    boolean insert(Shopping s);

    List<Shopping> selectAll();

    Shopping selectById(int id);

    boolean update(int id, Shopping s);

    boolean delete(int id);
}
