package com.learning.blob;

import com.learning.util.JDBCUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class BlobTest {

    /**
     * 插入
     */
    @Test
    public void testInsert() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "insert into users(name, password, birthday, photo) values (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, "Kun");
            ps.setObject(2, "198729");
            ps.setObject(3, "1987-02-09");
            FileInputStream is = new FileInputStream(new File(ClassLoader.getSystemClassLoader().getResource("photo.jpeg").getPath()));
            ps.setBlob(4, is);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn, ps);
        }
    }

    /**
     * 获取 Blob 数据
     */
    @Test
    public void testQuery() {
        Connection conn = null;
        PreparedStatement ps = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select name, password, birthday, photo from users where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, 5);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString(1); //或 rs.getString("name")，下同
                String password = rs.getString(2);
                Date birthday = rs.getDate(3);
                System.out.println(name + password + birthday.toString());
                Blob photo = rs.getBlob(4);
                is = photo.getBinaryStream();
                fos = new FileOutputStream(ClassLoader.getSystemClassLoader().getResource("").getPath() + "test.jpeg");
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (fos != null) fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JDBCUtil.closeResource(conn, ps);
        }
    }
}
