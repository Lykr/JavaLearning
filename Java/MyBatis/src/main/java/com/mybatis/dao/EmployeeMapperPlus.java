package com.mybatis.dao;

import com.mybatis.bean.Employee;

import java.util.List;

public interface EmployeeMapperPlus {

    public Employee getById(Integer id);

    public Employee getByIdStep(Integer id);

    public List<Employee> getByDeptId(Integer id);
}
