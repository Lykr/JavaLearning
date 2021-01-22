package com.learning.statement;

import com.learning.connection.ConnectionTest;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class StatementTest {

    @Test
    public void testLogin() throws Exception {
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

        Connection connection = DriverManager.getConnection(url, user, password); //获取连接对象

        Statement statement = connection.createStatement(); //通过连接获取 Statement 对象

        String sql = "SELECT * FROM USER";
        statement.execute(sql);
    }
}
