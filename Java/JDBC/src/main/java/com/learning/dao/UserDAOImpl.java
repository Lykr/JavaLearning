package com.learning.dao;

import com.learning.bean.User;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class UserDAOImpl extends BaseDAO implements UserDAO {
    @Override
    public void insert(Connection connection, User user) {
        String sql = "insert into users(name, password, birthday)values(?, ?, ?)";
        update(connection, sql, user.getName(), user.getPassword(), user.getBirthday());
    }

    @Override
    public void deleteById(Connection connection, int id) {
        String sql = "delete from users where id = ?";
        update(connection, sql, id);
    }

    @Override
    public void update(Connection connection, User user) {
        String sql = "update users set name = ?, password = ?, birthday = ? where id = ?";
        update(connection, sql, user.getName(), user.getPassword(), user.getBirthday(), user.getId());
    }

    @Override
    public User getUserById(Connection connection, int id) {
        String sql = "select * from users where id = ?";
        return getInstance(connection, User.class, sql, id);
    }

    @Override
    public List<User> getUserList(Connection connection) {
        String sql = "select * from users";
        return getInstanceList(connection, User.class, sql);
    }

    @Override
    public Long getCount(Connection connection) {
        String sql = "select count(*) from users";
        return getValue(connection, sql);
    }

    @Override
    public Date getMaxBirthday(Connection connection) {
        String sql = "select max(birthday) from users";
        return getValue(connection, sql);
    }
}
