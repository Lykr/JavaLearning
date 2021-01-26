package com.mybatis.test;

import com.mybatis.bean.Employee;
import com.mybatis.dao.EmployeesMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MyBatisTest {

    //基础获取方法
    @Test
    public void test1() throws IOException {
        String resource = "mybatis-config.xml"; //配置文件位置
        InputStream inputStream = Resources.getResourceAsStream(resource); //获取配置文件的输入流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); //获取 SqlSession 工厂类

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Employee employee = (Employee) session.selectOne("com.mybatis..selectEmployee", 1); //通过配置文件获取 SQL 语句
            System.out.println(employee);
        }
    }

    //获取 SqlSessionFactory
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml"; //配置文件位置
        InputStream inputStream = Resources.getResourceAsStream(resource); //获取配置文件的输入流
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    //单参数查
    @Test
    public void test2() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeesMapper mapper = session.getMapper(EmployeesMapper.class); //运用接口生成代理对象
            Employee employee = mapper.getById(1); //通过代理对象执行 SQL 语句
            System.out.println(employee);
        }
    }

    //增
    @Test
    public void test3() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeesMapper mapper = session.getMapper(EmployeesMapper.class); //运用接口生成代理对象
            Employee e = new Employee(null, "Chen", "Chen@learning.com", "1");
            mapper.add(e);
            session.commit(); //默认不自动提交，需要手动提交
            System.out.println("添加" + e + "成功");
        }
    }

    //改
    @Test
    public void test4() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeesMapper mapper = session.getMapper(EmployeesMapper.class); //运用接口生成代理对象
            Employee e = new Employee(3, "Chen", "Chen@learning.com", "0");
            mapper.update(e);
            session.commit();
            System.out.println("修改" + e + "成功");
        }
    }

    //删
    @Test
    public void test5() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeesMapper mapper = session.getMapper(EmployeesMapper.class); //运用接口生成代理对象
            mapper.deleteById(3);
            session.commit();
            System.out.println("删除成功");
        }
    }

    //多参数查
    @Test
    public void test6() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeesMapper mapper = session.getMapper(EmployeesMapper.class); //运用接口生成代理对象
            Employee employee = mapper.getByIdAndLastName(1, "tom"); //通过代理对象执行 SQL 语句
            System.out.println(employee);
        }
    }

    //Map 查
    @Test
    public void test7() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeesMapper mapper = session.getMapper(EmployeesMapper.class); //运用接口生成代理对象
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("lastName", "tom");
            Employee employee = mapper.getByMap(map); //通过代理对象执行 SQL 语句
            System.out.println(employee);
        }
    }
}
