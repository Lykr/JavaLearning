package com.mybatis.dao;

import com.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface EmployeesMapper {

    public Employee getById(Integer id);

    public void add(Employee e);

    public void update(Employee e);

    public void deleteById(Integer id);

    public Employee getByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    public Employee getByMap(Map<String, Object> map);
}
