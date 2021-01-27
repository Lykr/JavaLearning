package com.mybatis.dao;

import com.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    public Employee getById(Integer id);

    public void add(Employee e);

    public void update(Employee e);

    public void deleteById(Integer id);

    public Employee getByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    public Employee getByMap(Map<String, Object> map);

    public List<Employee> getByLastNameLike(String lastName);

    public Map<String, Object> getByIdReturnMap(Integer id);

    @MapKey("id") //告诉 MyBatis 封装这个 Map 的时候使用 Bean 的哪个属性作为键
    public Map<Integer, Employee> getByLastNameLikeReturnMap(String lastName);
}
