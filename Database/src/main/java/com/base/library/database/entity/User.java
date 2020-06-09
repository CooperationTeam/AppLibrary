package com.base.library.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author reber
 */
@Entity
public class User {

    @PrimaryKey
    private String userId;

    @ColumnInfo(name = "user_name")
    private String username;

    private String age;
    private String phone;
}
