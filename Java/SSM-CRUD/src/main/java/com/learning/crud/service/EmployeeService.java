package com.learning.crud.service;

import com.learning.crud.bean.Employee;
import com.learning.crud.bean.EmployeeExample;
import com.learning.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    public boolean checkUser(String name) {
        EmployeeExample ee = new EmployeeExample();
        EmployeeExample.Criteria criteria = ee.createCriteria();
        criteria.andNameEqualTo(name);
        long count = employeeMapper.countByExample(ee);
        return count == 0;
    }

    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public void delEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void delEmpBatch(List<Integer> ids) {
        EmployeeExample ee = new EmployeeExample();
        EmployeeExample.Criteria criteria = ee.createCriteria();
        criteria.andIdIn(ids);
        employeeMapper.deleteByExample(ee);
    }
}
