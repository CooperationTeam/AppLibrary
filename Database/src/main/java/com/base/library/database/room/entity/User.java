package com.base.library.database.room.entity;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @author reber
 */
@Entity(tableName = "USER") // 设置表名字。默认是类的名字
public class User {

    @ColumnInfo(name = "user_id")
    @PrimaryKey
    private @NonNull
    String userId;

    @ColumnInfo(name = "user_name")
    private String username;

    private String age;
    private String phone;

    @Embedded
    private Address address; // 使用@Embedded注解来表示嵌入

    @Ignore
    private Bitmap avatar; // @Ignore忽略avatar映射到表中

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }
}
