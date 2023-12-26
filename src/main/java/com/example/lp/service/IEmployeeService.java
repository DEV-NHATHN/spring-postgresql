package com.example.lp.service;

import com.example.lp.model.Employee;

import java.util.List;

public interface IEmployeeService {
public Employee add(Employee employee);

    public Employee update(long employee_code, Employee employee);

    public boolean delete(long employee_code);

    public List<Employee> getList();

    public Employee getOne(long employee_code);
}