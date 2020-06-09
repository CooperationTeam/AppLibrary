package com.base.library.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.base.library.database.entity.User;

import java.util.List;

@Dao
public interface UserDao extends EntityDao<User> {

    @Query("SELECT * FROM User")
    @Override
    List<User> queryAllEntities();
}
