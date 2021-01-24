package com.learning.dbutils;

import com.learning.bean.User;
import com.learning.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class QueryRunnerTest {

    @Test
    public void insert() {
        Connection connect = null;
        try {
            QueryRunner runner = new QueryRunner();
            connect = com.learning.datasource.util.JDBCUtils.getConnection3();
            String sql = "insert into users(name, birthday, password)values(?, ?, ?)";
            runner.update(connect, sql, "小红", "1996-09-01", "199691");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connect, null);
        }
    }

    //BeanHandler 返回一条记录
    @Test
    public void query1() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = com.learning.datasource.util.JDBCUtils.getConnection3();
            String sql = "select id, name, password, birthday from users where id = ?";
            BeanHandler<User> handler = new BeanHandler<User>(User.class); //BeanHandler 是 ResultSetHandler 的实现类
            User user = runner.query(connection, sql, handler, 3);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    //BeanListHandler 返回多条记录
    @Test
    public void query2() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = com.learning.datasource.util.JDBCUtils.getConnection3();
            String sql = "select id, name, password, birthday from users where id < ?";
            BeanListHandler<User> handler = new BeanListHandler<User>(User.class); //BeanHandler 是 ResultSetHandler 的实现类
            List<User> list = runner.query(connection, sql, handler, 10);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    //MapHandler 返回用 Map 表示的对象
    @Test
    public void query3() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = com.learning.datasource.util.JDBCUtils.getConnection3();
            String sql = "select id, name, password, birthday from users where id = ?";
            MapHandler handler = new MapHandler(); //MapHandler 是 ResultSetHandler 的实现类
            Map<String, Object> map = runner.query(connection, sql, handler, 3);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    //MapListHandler 返回用 Map 表示的多条记录
    @Test
    public void query4() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = com.learning.datasource.util.JDBCUtils.getConnection3();
            String sql = "select id, name, password, birthday from users where id < ?";
            MapListHandler handler = new MapListHandler(); //MapListHandler 是 ResultSetHandler 的实现类
            List<Map<String, Object>> list = runner.query(connection, sql, handler, 10);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    //ScalarHandler 返回标量
    @Test
    public void query5() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = com.learning.datasource.util.JDBCUtils.getConnection3();
            String sql = "select count(*) from users";
            ScalarHandler handler = new ScalarHandler(); //MapHandler 是 ResultSetHandler 的实现类
            Long count = (Long) runner.query(connection, sql, handler);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    //ScalarHandler 返回标量
    @Test
    public void query6() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = com.learning.datasource.util.JDBCUtils.getConnection3();
            String sql = "select max(birthday) from users";
            ScalarHandler handler = new ScalarHandler(); //MapHandler 是 ResultSetHandler 的实现类
            Date maxBirthday = (Date) runner.query(connection, sql, handler);
            System.out.println(maxBirthday);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    //自定义 ResultSetHandler
    @Test
    public void query7() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = com.learning.datasource.util.JDBCUtils.getConnection3();
            String sql = "select id,name,password,birthday from users where id = ?";
            ResultSetHandler<User> handler = new ResultSetHandler<User>() {
                @Override
                public User handle(ResultSet resultSet) throws SQLException {
                    if (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        String password = resultSet.getString(3);
                        Date birthday = resultSet.getDate(4);
                        return new User(id, name, birthday, password, null);
                    }
                    return null;
                }
            };
            User user = runner.query(connection, sql, handler, 3);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }
}
