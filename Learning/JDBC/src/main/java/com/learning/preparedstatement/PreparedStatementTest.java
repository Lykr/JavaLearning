package com.learning.preparedstatement;

import com.learning.connection.ConnectionTest;
import com.learning.util.JDBCUtils;
import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class PreparedStatementTest {

    /**
     * 增
     */
    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //读取配置文件的信息
            InputStream is = ConnectionTest.class.getResourceAsStream("/jdbc.properties"); //前面加 / 表示从根目录的 resources 目录获取文件，否则从类所在目录

            //加载配置文件
            Properties prop = new Properties();
            prop.load(is);

            //从配置文件获取配置信息
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            String url = prop.getProperty("url");
            String driverClass = prop.getProperty("driverClass");

            Class.forName(driverClass); //使用反射获取 Driver 对象

            connection = DriverManager.getConnection(url, user, password); //获取连接对象

            String sql = "insert into user(name,password,birthday)values(?,?,?)"; //准备 SQL 语句
            ps = connection.prepareStatement(sql); //获取 PreparedStatement

            //填充占位符
            ps.setString(1, "Heng");
            ps.setString(2, "198631");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1986-03-01");
            ps.setDate(3, new Date(date.getTime()));

            ps.execute(); //执行
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (ps != null) ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 改
     */
    @Test
    public void testUpdate1() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE user SET name = ? where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, "Hu");
            ps.setObject(2, 1);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, ps);
        }
    }

    /**
     * 通用增删改
     * @param sql
     * @param objs
     */
    public void update(String sql, Object...objs) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i + 1, objs[i]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, ps);
        }
    }

    @Test
    public void testUpdate2() {
        update("update user set name = 'Shi' where id = ?", 1);
    }
}
