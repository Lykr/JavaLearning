package com.mybatis.test;

import com.mybatis.bean.Department;
import com.mybatis.bean.Employee;
import com.mybatis.dao.DepartmentMapper;
import com.mybatis.dao.EmployeeMapper;
import com.mybatis.dao.EmployeeMapperDynamicSQL;
import com.mybatis.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class); //运用接口生成代理对象
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
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class); //运用接口生成代理对象
            Employee e = new Employee(null, "Xie", "Xie@learning.com", "1");
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
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class); //运用接口生成代理对象
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
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class); //运用接口生成代理对象
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
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class); //运用接口生成代理对象
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
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class); //运用接口生成代理对象
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("lastName", "tom");
            Employee employee = mapper.getByMap(map); //通过代理对象执行 SQL 语句
            System.out.println(employee);
        }
    }

    //查询返回 List
    @Test
    public void test8() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class); //运用接口生成代理对象
            List<Employee> employees = mapper.getByLastNameLike("%e%"); //通过代理对象执行 SQL 语句
            employees.forEach(System.out::println);
        }
    }

    //查询单个记录按 Map 返回，Map<String, Object>
    @Test
    public void test9() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class); //运用接口生成代理对象
            Map<String, Object> employee = mapper.getByIdReturnMap(1); //通过代理对象执行 SQL 语句
            System.out.println(employee);
        }
    }

    //查询多条记录按 Map 返回，Map<Integer, Employee>
    @Test
    public void test10() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class); //运用接口生成代理对象
            Map<Integer, Employee> employees = mapper.getByLastNameLikeReturnMap("%e%"); //通过代理对象执行 SQL 语句
            System.out.println(employees);
        }
    }

    //使用 ResultMap 查询单条记录
    @Test
    public void test11() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class); //运用接口生成代理对象
            Employee employee = mapper.getById(1); //通过代理对象执行 SQL 语句
            System.out.println(employee);
            System.out.println(employee.getDept());
        }
    }

    //使用 ResultMap 配合 Association 进行分步查询
    @Test
    public void test12() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class); //运用接口生成代理对象
            Employee employee = mapper.getByIdStep(1); //通过代理对象执行 SQL 语句
            System.out.println(employee);
            System.out.println(employee.getDept());
        }
    }

    //使用 ResultMap 配合 Collection 进行对集合属性的查询
    @Test
    public void test13() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DepartmentMapper mapper = session.getMapper(DepartmentMapper.class); //运用接口生成代理对象
            Department dept = mapper.getByIdPlus(1); //通过代理对象执行 SQL 语句
            System.out.println(dept);
        }
    }

    //使用 ResultMap 配合 Collection 进行分步查询
    @Test
    public void test14() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DepartmentMapper mapper = session.getMapper(DepartmentMapper.class); //运用接口生成代理对象
            Department dept = mapper.getByIdStep(1); //通过代理对象执行 SQL 语句
            System.out.println(dept);
        }
    }

    //Dynamic SQL 测试，where 标签
    @Test
    public void test15() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class); //运用接口生成代理对象
            List<Employee> list = mapper.getEmpsByConditionIf(new Employee(1, "%o%","tom@tom.com", null)); //通过代理对象执行 SQL 语句
            list.forEach(System.out::println);
        }
    }

    //Dynamic SQL 测试，trim 标签
    @Test
    public void test16() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class); //运用接口生成代理对象
            List<Employee> list = mapper.getEmpsByConditionTrim(new Employee(null, "%o%","tom@tom.com", null)); //通过代理对象执行 SQL 语句
            list.forEach(System.out::println);
        }
    }

    //Dynamic SQL 测试，Choose 标签
    @Test
    public void test17() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class); //运用接口生成代理对象
            List<Employee> list = mapper.getEmpsByConditionChoose(new Employee(null, null,null, null)); //通过代理对象执行 SQL 语句
            list.forEach(System.out::println);
        }
    }

    //Dynamic SQL 测试，update，set 标签
    @Test
    public void test18() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class); //运用接口生成代理对象
            Employee newEmployee = new Employee(1, "tom", "tom@tom.com", null );
            mapper.updateEmp(newEmployee);
            session.commit();
        }
    }

    //Dynamic SQL 测试，update，trim 标签
    @Test
    public void test19() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class); //运用接口生成代理对象
            Employee newEmployee = new Employee(1, "tom", "tom@tom.com", null );
            mapper.updateEmpTrim(newEmployee);
            session.commit();
        }
    }

    //Dynamic SQL 测试，foreach 批量查询
    @Test
    public void test20() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class); //运用接口生成代理对象
            List<Employee> list = mapper.getEmpsByConditionForeach(Arrays.asList(1,2,3,4,5,6));
            list.forEach(System.out::println);
        }
    }

    //Dynamic SQL 测试，foreach 批量插入
    @Test
    public void test21() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class); //运用接口生成代理对象
            List<Employee> list = new ArrayList<>();
            list.add(new Employee(null, "Wu", "wu@wu.com", "1", new Department(1, "EngDept")));
            list.add(new Employee(null, "Xu", "Xu@wu.com", "0", new Department(1, "EngDept")));
            list.add(new Employee(null, "Cai", "Cai@wu.com", "1", new Department(2, "TesDept")));
            mapper.addEmps(list);
            session.commit();
            System.out.println("添加成功");
        }
    }

    //Dynamic SQL 测试，内置参数测试
    @Test
    public void test22() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class); //运用接口生成代理对象
            Employee e = new Employee();
//            e.setLastName("%e%");
            e.setLastName("e"); //不建议使用 bind 来进行模糊查询的拼接
            List<Employee> list = mapper.getEmpsTestInnerParameter(e);
            list.forEach(System.out::println);
        }
    }

    //一级缓存测试，sqlsession 级别的缓存，默认开启无法关闭
    @Test
    public void test23() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session1 = sqlSessionFactory.openSession();
             SqlSession session2 = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper1 = session1.getMapper(EmployeeMapper.class); //运用接口生成代理对象
            EmployeeMapper mapper2 = session2.getMapper(EmployeeMapper.class); //运用接口生成代理对象
            Employee e1 = mapper1.getById(1); //第一次查询
            System.out.println(e1);
            Employee e2 = mapper1.getById(1); //第二次查询
            Employee e3 = mapper2.getById(1); //第三次查询
            System.out.println(e1 == e2); //true，同一 sqlsession 下会用缓存
            System.out.println(e1 == e3); //false，不同同 sqlsession 下会缓存不共享
            mapper1.update(new Employee(4, "Chen", "Chen@learning.com", "1", null)); //增删改的 flushCache 为 true
            Employee e4 = mapper1.getById(1); //第四次查询
            System.out.println(e1 == e4); //false，同一 sqlsession 同一查询语句间使用增删改语句会删除缓存
            session1.clearCache(); //清空一级缓存
        }
    }

    //二级缓存测试，namespace（不同的 Mapper）级别的缓存，**session 关闭**后将会将缓存放入二级缓存
    //1. 通过配置 settings 中的 cacheEnabled 来开启，默认开启但建议显式设置
    //2. 进入 mapper.xml 添加 <cache/> 标签开启
    //3. POJO 需要实现序列化接口
    @Test
    public void test24() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //通过工厂类获取 SqlSession，并通过 SqlSession 执行 SQL 语句
        try (SqlSession session1 = sqlSessionFactory.openSession();
             SqlSession session2 = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper1 = session1.getMapper(EmployeeMapper.class); //运用接口生成代理对象
            EmployeeMapper mapper2 = session2.getMapper(EmployeeMapper.class); //运用接口生成代理对象
            Employee e1 = mapper1.getById(1); //第一次查询
            System.out.println(e1);
            session1.close();
            Employee e2 = mapper2.getById(1); //第二次查询，从二级缓存中拿到的数据
            System.out.println(e1 == e2); //但是 e1 不等于 e2
        }
    }
}
