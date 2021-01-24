package com.learning.newdao;

import com.learning.bean.User;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 用于 User 表的常用操作
 */
public interface UserDAO {
    // 将 user 对象添加到数据库
    void insert(Connection connection, User user);

    //根据 id 删除记录
    void deleteById(Connection connection, int id);

    //根据对象修改记录
    void update(Connection connection, User user);

    //根据 id 查一条记录
    User getUserById(Connection connection, int id);

    //查询多条记录
    List<User> getUserList(Connection connection);

    //返回数据表数据的条目数
    Long getCount(Connection connection);

    Date getMaxBirthday(Connection connection);
}
