package com.learning.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {

    public static Connection getConnection() throws Exception {
        //读取配置文件的信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties"); //系统级加载器获取根目录资源前面不用加 /

        //加载配置文件
        Properties prop = new Properties();
        prop.load(is);

        //从配置文件获取配置信息
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String url = prop.getProperty("url");
        String driverClass = prop.getProperty("driverClass");

        Class.forName(driverClass); //使用反射获取 Driver 对象

        Connection connection = DriverManager.getConnection(url, user, password); //获取连接对象
        return connection;
    }

    public static void closeResource(Connection connection, PreparedStatement ps) {
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
