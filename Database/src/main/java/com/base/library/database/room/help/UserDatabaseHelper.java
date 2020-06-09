package com.base.library.database.room.help;

import com.base.library.database.room.dao.UserDao;
import com.base.library.database.room.entity.User;

import java.util.List;

/**
 * @author reber
 */
public class UserDatabaseHelper {

    private UserDao mUserDao;

    UserDatabaseHelper(UserDao userDao) {
        this.mUserDao = userDao;
    }

    public List<User> queryAllUsers() {
        return mUserDao.queryAllUsers();
    }

    public long queryUserById(String userId) {
        return mUserDao.queryUserById(userId);
    }

    public long insertUser(User user) {
        return mUserDao.insertUser(user);
    }

    public long[] insertUserList(List<User> userList) {
        return mUserDao.insertUserList(userList);
    }

    public int deleteUser(User user) {
        return mUserDao.deleteUser(user);
    }

    public int deleteUserList(List<User> userList) {
        return mUserDao.deleteUserList(userList);
    }
}
