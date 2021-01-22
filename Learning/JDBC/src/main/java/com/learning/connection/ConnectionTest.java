package com.learning.connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {

    //最基础的方法
    @Test
    public void testConnection1() throws SQLException {
        //获取 MySQL 数据库驱动
        Driver driver = new com.mysql.cj.jdbc.Driver();

        //设置数据库连接信息
        String url = "jdbc:mysql://localhost:3306/learning_jdbc";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "199761");

        //获取连接
        Connection connection = driver.connect(url, info);

        System.out.println(connection);
    }

    //通过反射加载第三方依赖，提高可移植性
    @Test
    public void testConnection2() throws Exception {
        //使用反射获取 Driver 对象
        Class driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) driverClass.getDeclaredConstructor().newInstance(); //Java 9 之后 Class.newInstance() 改为 Class.getDeclaredConstructor().newInstance()

        //设置数据库连接信息
        String url = "jdbc:mysql://localhost:3306/learning_jdbc";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "199761");

        //获取连接
        Connection connection = driver.connect(url, info);

        System.out.println(connection);
    }

    //使用 DriverManager 替换 Driver
    @Test
    public void testConnection3() throws Exception {
        //使用反射获取 Driver 对象
        Class driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) driverClass.getDeclaredConstructor().newInstance(); //Java 9 之后 Class.newInstance() 改为 Class.getDeclaredConstructor().newInstance()

        //注册驱动
        DriverManager.registerDriver(driver);

        //设置数据库连接信息
        String url = "jdbc:mysql://localhost:3306/learning_jdbc";
        String user = "root";
        String password = "199761";

        //从注册的 DriverManager 获取连接
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }

    //省略 DriverManager 注册 Driver 的代码（加载 Driver 类时，静态代码块已经自动加载）
    @Test
    public void testConnection4() throws Exception {
        //使用反射获取 Driver 对象
        Class.forName("com.mysql.cj.jdbc.Driver");

        //设置数据库连接信息
        String url = "jdbc:mysql://localhost:3306/learning_jdbc";
        String user = "root";
        String password = "199761";

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }

    //将建立数据库连接的 4 个信息存储在配置文件中，通过读取配置文件的方式进行加载
    /*
    1 实现了数据和代码的分离（解耦）
    2 可直接修改配置信息，避免重新打包
     */
    @Test
    public void testConnection5() throws Exception {
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


        //使用反射获取 Driver 对象
        Class.forName(driverClass);

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }
}
