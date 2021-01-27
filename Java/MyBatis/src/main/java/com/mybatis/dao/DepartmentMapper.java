package com.mybatis.dao;

import com.mybatis.bean.Department;
import com.mybatis.bean.Employee;

import java.util.List;

public interface DepartmentMapper {

    public Department getById(Integer id);

    public Department getByIdPlus(Integer id);

    public Department getByIdStep(Integer id);
}
