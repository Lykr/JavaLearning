package com.learning.crud.test;

import com.learning.crud.bean.Department;
import com.learning.crud.bean.Employee;
import com.learning.crud.dao.DepartmentMapper;
import com.learning.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCRUD() {
        //1. 创建 SpringIOC 容器
        //ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2. 从容器中获取 Mapper
        //ioc.getBean(DepartmentMapper.class);
        //System.out.println(departmentMapper);
        //
        //1. 插入部门
        //departmentMapper.insertSelective(new Department(null, "开发部"));
        //departmentMapper.insertSelective(new Department(null, "测试部"));
        //2. 插入员工
        //employeeMapper.insert(new Employee(null, "Tom", "M", "Tom@learning.com", 1, null));
        //3. 批量插入
        //EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //for (int i = 0; i < 1000; i++) {
        //    String uid = UUID.randomUUID().toString().substring(0, 5) + i;
        //    String gender = Math.random() > 0.5f ? "M" : "F";
        //    mapper.insert(new Employee(null, uid, gender, uid + "@learning.com", 1, null));
        //}
        //System.out.println("批量插入完成");
    }
}
