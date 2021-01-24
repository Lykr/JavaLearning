package com.learning.dao;

import com.learning.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装了对于数据表的基本操作（考虑了事务）
 */
public abstract class BaseDAO {
    //通用增删改
    public int update(Connection connection, String sql, Object...objs) {
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
            JDBCUtils.closeResource(null, ps);
        }
        return 0;
    }

    //通用单对象查询
    public <T> T getInstance(Connection connection, Class<T> clazz, String sql, Object... objs) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql); //准备 PreparedStatement

            //填充占位符
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i + 1, objs[i]);
            }

            rs = ps.executeQuery(); //获取结果集
            ResultSetMetaData rsmd = rs.getMetaData(); //获取结果集元数据
            int columnCount = rsmd.getColumnCount(); //获取结果集列数

            if (rs.next()) {
                T t = clazz.getDeclaredConstructor().newInstance();

                //处理结果集中的每一列
                for (int i = 1; i <= columnCount; i++) {
                    Object columnValue = rs.getObject(i); //获取列值
                    String columnLabel = rsmd.getColumnLabel(i); //获取列名

                    //给 t 对象赋值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }

                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
             JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    //通用多对象查询
    public <T> List<T> getInstanceList(Connection connection, Class<T> clazz, String sql, Object... objs) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql); //准备 PreparedStatement

            //填充占位符
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i + 1, objs[i]);
            }

            rs = ps.executeQuery(); //获取结果集
            ResultSetMetaData rsmd = rs.getMetaData(); //获取结果集元数据
            int columnCount = rsmd.getColumnCount(); //获取结果集列数

            List<T> list = new ArrayList<>();

            while (rs.next()) {
                T t = clazz.getDeclaredConstructor().newInstance(); //生成对象实例

                //处理结果集中的每一列
                for (int i = 1; i <= columnCount; i++) {
                    Object columnValue = rs.getObject(i); //获取列值
                    String columnLabel = rsmd.getColumnLabel(i); //获取列名

                    //给 t 对象赋值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }

                list.add(t);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    public <E> E getValue(Connection connection, String sql, Object... objs) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i + 1, objs[i]);
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
}
