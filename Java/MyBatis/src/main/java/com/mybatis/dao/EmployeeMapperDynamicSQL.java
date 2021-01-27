package com.mybatis.dao;

import com.mybatis.bean.Employee;

import java.util.List;

public interface EmployeeMapperDynamicSQL {

    public List<Employee> getEmpsByConditionIf(Employee employee);

    public List<Employee> getEmpsByConditionTrim(Employee employee);

    public List<Employee> getEmpsByConditionChoose(Employee employee);

    public void updateEmp(Employee employee);

    public void updateEmpTrim(Employee employee);

    public List<Employee> getEmpsByConditionForeach(List<Integer> list);

    public void addEmps(List<Employee> list);

    public List<Employee> getEmpsTestInnerParameter(Employee employee);
}
