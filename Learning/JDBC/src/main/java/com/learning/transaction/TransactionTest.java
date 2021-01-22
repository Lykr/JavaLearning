package com.learning.transaction;

import com.learning.util.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Note:
 * 数据一旦提交（commit）不可回滚（rollback）
 * 数据定义语言（Data Definition Language, DDL）一定自动提交
 * 数据操纵语言（Data Manipulation Language, DML）默认自动提交，通过 setAutoCommit(false) 取消（数据库用 set autocommit = false）
 * 连接关闭时会将没有执行的语句提交
 */

public class TransactionTest {
    //通用增删改，未考虑数据库事务的通用操作
    public int update(String sql, Object...objs) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i + 1, objs[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(connection, ps);
        }
        return 0;
    }

    /**
     * 用户 A 给用户 B 转账 100
     * SQL 语句：
     *  update accounts set balance = balance - 100 where user = 'A'
     *  update accounts set balance = balance + 100 where user = 'B'
     */
    @Test
    public void testUpdate() {
        String sql1 = "update accounts set balance = balance - 100 where name = ?";
        update(sql1, "A");

        int a = 1 / 0; //模拟异常

        String sql2 = "update accounts set balance = balance + 100 where name = ?";
        update(sql2, "B");

        System.out.println("转账成功");
    }

    //考虑数据库事务的通用操作
    public int transactionUpdate(Connection connection, String sql, Object...objs) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i + 1, objs[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(null, ps);
        }
        return 0;
    }

    /**
     * 事务型操作流程：
     *  1. 创建连接
     *  2. 取消自动提交
     *  3. 通过 PreparedStatement 传入多条语句，并关闭
     *  4. 捕获异常进行回滚
     *  5. 关闭连接
     */
    @Test
    public void testTransactionUpdate() {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false); //取消自动提交

            String sql1 = "update accounts set balance = balance - 100 where name = ?";
            transactionUpdate(connection, sql1, "A");

            int a = 1 / 0; //模拟异常

            String sql2 = "update accounts set balance = balance + 100 where name = ?";
            transactionUpdate(connection, sql2, "B");

            connection.commit();
            System.out.println("转账成功");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback(); //回滚
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true); //将连接自动提交状态重置为 true，避免使用数据库连接池导致出错
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JDBCUtil.closeResource(connection, null);
        }
    }
}
