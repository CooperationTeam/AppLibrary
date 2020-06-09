package com.base.library.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.base.library.database.room.entity.User;

import java.util.List;

/**
 * onConflict：默认值是OnConflictStrategy.ABORT
 * 1. OnConflictStrategy.REPLACE：冲突策略是取代旧数据同时继续事务。
 * 2. OnConflictStrategy.ROLLBACK：冲突策略是回滚事务。
 * 3. OnConflictStrategy.ABORT：冲突策略是终止事务。
 * 4. OnConflictStrategy.FAIL：冲突策略是事务失败。
 * 5. OnConflictStrategy.IGNORE：冲突策略是忽略冲突。
 */
@Dao
public interface UserDao {
    /**
     * 查询USER表中的所有用户
     */
    @Query("SELECT * FROM USER")
    List<User> queryAllUsers();

    /**
     * 根据用户Id查询用户
     */
    @Query("SELECT * FROM USER WHERE user_id = :userId")
    long queryUserById(String userId);

    /**
     * 插入一条User数据
     * <p>
     * long返回插入数据成功后的主键Id
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertUser(User user);

    /**
     * 插入多条User数据
     * <p>
     * long[]返回插入数据成功后的主键Id列表ø
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertUserList(List<User> userList);

    /**
     * 删除一条User数据
     */
    @Delete
    int deleteUser(User user);

    /**
     * 删除多条User数据
     */
    @Delete
    int deleteUserList(List<User> userList);
}
