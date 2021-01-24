package com.learning.datasource.connection;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest {
    @Test
    public void getConnection() throws Exception{
        Properties prop = new Properties();
        prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties"));
        DataSource source = DruidDataSourceFactory.createDataSource(prop);
        Connection connection = source.getConnection();
        System.out.println(connection);
    }
}
