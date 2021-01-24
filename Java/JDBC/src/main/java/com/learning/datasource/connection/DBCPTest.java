package com.learning.datasource.connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

public class DBCPTest {

    @Test
    public void testGetConnection1() throws Exception {
        BasicDataSource source = new BasicDataSource();

        //基本信息
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/learning_jdbc");
        source.setUsername("root");
        source.setPassword("199761");

        //其他设置
        source.setInitialSize(10);
        source.setMaxTotal(10);

        Connection connection = source.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testGetConnection2() throws Exception {
        Properties prop = new Properties();
        prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties"));
        DataSource source = BasicDataSourceFactory.createDataSource(prop);
        Connection connection = source.getConnection();
        System.out.println(connection);
    }
}
