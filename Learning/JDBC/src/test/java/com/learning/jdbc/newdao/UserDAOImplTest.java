package com.learning.jdbc.newdao;

import com.learning.bean.User;
import com.learning.newdao.UserDAOImpl;
import com.learning.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class UserDAOImplTest {

    UserDAOImpl dao = new UserDAOImpl();

    @Test
    public void insert() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            User user = new User(0, "小王", new Date(123235234212312L), "19771212", null);
            dao.insert(connection, user);
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void deleteById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            dao.deleteById(connection, 6);
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void update() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            User user = new User(4, "小王", new Date(123234212312L), "19771212", null);
            dao.update(connection, user);
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void getUserById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            User user = dao.getUserById(connection, 4);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void getUserList() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            List<User> userList = dao.getUserList(connection);
            userList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void getCount() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            long count = dao.getCount(connection);
            System.out.println("表中记录数为：" + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void getMaxBirthday() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Date maxBirthday = dao.getMaxBirthday(connection);
            System.out.println("最晚的生日为：" + maxBirthday);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }
}