package com.base.library.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.base.library.database.dao.UserDao;
import com.base.library.database.entity.User;

@Database(entities = {(User.class)}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
