package com.learning.datasource.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;

public class C3P0Test {
    @Test
    public void testGetConnection1() throws Exception{
        ComboPooledDataSource cpds = new ComboPooledDataSource(); //获取 C3P0 数据库连接池
        cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/learning_jdbc");
        cpds.setUser("root");
        cpds.setPassword("123456");

        cpds.setInitialPoolSize(10); //初始时数据库连接池中的连接数

        Connection connection = cpds.getConnection(); //通过连接池获取连接
        System.out.println(connection);

        //销毁连接池
//        DataSources.destroy(cpds);
    }

    @Test
    public void testGetConnection2() {
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        try (Connection connection = cpds.getConnection()) {
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
