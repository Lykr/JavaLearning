package com.learning.datasource.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

public class JDBCUtils {

    //获取 C3P0 DataSource
    private static final ComboPooledDataSource c3p0Source = new ComboPooledDataSource("helloc3p0");

    //获取 DBCP DataSource
    private static DataSource dbcpSource;
    static {
        try {
            Properties prop = new Properties();
            prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties"));
            dbcpSource = BasicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取 C3P0 DataSource
    private static DataSource druidSource;
    static {
        try {
            Properties prop = new Properties();
            prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties"));
            druidSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //使用 C3P0 获取连接
    public static Connection getConnection1() throws Exception {
        return c3p0Source.getConnection();
    }

    //使用 DBCP 获取连接
    public static Connection getConnection2() throws Exception {
        return dbcpSource.getConnection();
    }

    //使用 Druid 获取连接
    public static Connection getConnection3() throws Exception {
        return druidSource.getConnection();
    }
}
