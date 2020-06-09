package com.base.library.database.room.help;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;

import com.base.library.database.room.AppDatabase;

/**
 * @author reber
 */
public class AppDatabaseHelper {

    private volatile static AppDatabaseHelper instance;
    private AppDatabase mAppDatabase;

    private AppDatabaseHelper() {
    }

    public static AppDatabaseHelper getInstance() {
        if (instance == null) {
            synchronized (AppDatabaseHelper.class) {
                if (instance == null) {
                    instance = new AppDatabaseHelper();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        initWithMigration(context);
    }

    public void initWithMigration(Context context, Migration... migrations) {
        RoomDatabase.Builder<AppDatabase> databaseBuilder = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "android_room_dev.db")
                .allowMainThreadQueries();//允许在主线程做查询操作
        if (migrations != null && migrations.length > 0) {
            databaseBuilder
                    .addMigrations(migrations) //设置数据库升级(迁移)的逻辑
                    .fallbackToDestructiveMigration();//设置迁移数据库如果发生错误，将会重新创建数据库，而不是发生崩溃

        }
        this.mAppDatabase = databaseBuilder.build();
    }

    public UserDatabaseHelper getUserDatabaseHelper() {
        if (mAppDatabase == null) {
            throw new ExceptionInInitializerError("please init AppDatabase before want to use database");
        }
        return new UserDatabaseHelper(mAppDatabase.userDao());
    }
}
