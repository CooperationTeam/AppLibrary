package com.base.library.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import java.util.List;

@Dao
public interface EntityDao<T> {

    List<T> queryAllEntities();

    @Insert
    void insertEntities(T[] t);

    @Insert
    void insertEntity(T t);

    @Delete
    void deleteEntity(T t);
}
