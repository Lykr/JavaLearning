package com.learning.preparedstatement;

import com.learning.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 本类演示使用 PreparedStatement 进行批量插入操作
 *
 * 数据库操作：
 * drop table if exists goods;
 * create table goods(
 * id int primary key auto_increment,
 * name varchar(25)
 * );
 * select * from goods;
 *
 * 需求：向 goods 表中插入 20000 条数据
 */
public class MultiInsertTest {

    public void testInsert1() {
        //使用 Statement 进行
        // 1. 获取连接
        // 2. 获取 Statement
        // 3. 循环设置 SQL 语句并通过 Statement 执行
    }

    /**
     * 使用 PrepareStatement 进行批量插入
     */
    @Test
    public void testInsert2() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 20000; i++) {
                ps.setObject(1, "name_" + i);
                ps.execute();
            }

            long end = System.currentTimeMillis();
            System.out.println("花费的时间：" + (end - start) + "ms"); //11130ms
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }

    /**
     * 使用 batch 方法
     * Note: 1. MySQL 默认关闭批处理操作，需要在 URL 后添加参数 rewriteBatchedStatements=true
     *       2. MySQL 5.1.13 及以上的版本才支持使用 Batch 进行批量处理
     */
    @Test
    public void testInsert3() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 1000000; i++) {
                ps.setObject(1, "name_" + i);
                ps.addBatch(); //累计 SQL 语句到 batch
                if (i % 500 == 0) {
                    ps.executeBatch(); //执行 batch
                    ps.clearBatch(); //清空 batch
                }
            }
            ps.executeBatch();
            ps.clearBatch();

            long end = System.currentTimeMillis();
            System.out.println("花费的时间：" + (end - start) + "ms"); //20000-992ms，1000000-9824ms
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }

    @Test
    public void testInsert4() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false); //不允许提交
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 1000000; i++) {
                ps.setObject(1, "name_" + i);
                ps.addBatch(); //累计 SQL 语句到 batch
                if (i % 500 == 0) {
                    ps.executeBatch(); //执行 batch
                    ps.clearBatch(); //清空 batch
                }
            }
            ps.executeBatch();
            ps.clearBatch();

            conn.commit(); //提交语句

            long end = System.currentTimeMillis();
            System.out.println("花费的时间：" + (end - start) + "ms"); //20000-992ms，1000000-8966ms
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }
}
