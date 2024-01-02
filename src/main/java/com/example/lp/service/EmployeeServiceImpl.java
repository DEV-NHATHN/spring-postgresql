package com.example.lp.service;

import com.example.lp.IEmployeeSpecifications;
import com.example.lp.entity.Employee;
import com.example.lp.exception.BadRequestException;
import com.example.lp.model.CreateEmployeeRequest;
import com.example.lp.model.EmployeeDTO;
import com.example.lp.model.ModelMapper;
import com.example.lp.repository.EmployeeRepository;
import com.example.lp.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements IEmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
        this.employeeRepository = employeeRepo;
    }

    public boolean isEmployeeExists(long employeeCode) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeCode);
        return employeeOptional.isPresent();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 30)
    public List<EmployeeDTO> getEmployees(String branchCode, Boolean status) {
        Specification<Employee> spec = Specification.where(null);

        if (branchCode != null) {
            spec = spec.and(IEmployeeSpecifications.hasBranchCode(branchCode));
        }

        if (status != null) {
            spec = spec.and(IEmployeeSpecifications.hasStatus(status));
        }

        List<Employee> employees = employeeRepository.findAll(spec);

        List<EmployeeDTO> result = new ArrayList<>();
        for (Employee employee : employees) {
            result.add(ModelMapper.toEmployeeDTO(employee));
        }
        return result;
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRES_NEW,
            timeout = 30)
    @Override
    public Employee add(CreateEmployeeRequest request) throws BadRequestException {
        if (!Util.isValidString(request.getName())) {
            throw new BadRequestException("Name is not valid!");
        }

        if (!Util.isValidNumber(request.getAge())) {
            throw new BadRequestException("Age is not valid!");
        }

        if (!Util.isValidString(request.getBranch_code())) {
            throw new BadRequestException("Branch code is not valid!");
        }

        if (!Util.isValidString(request.getAddress())) {
            throw new BadRequestException("Address code is not valid!");
        }

        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setAge(request.getAge());
        employee.setBranch_code(request.getBranch_code());
        employee.setStatus(request.isStatus());
        employee.setAddress(request.getAddress());
        employee.setSecret_key(request.getSecret_key());

        Employee employee2 = new Employee();
        employee2.setName(request.getName());
        employee2.setAge(request.getAge());
        employee2.setBranch_code(request.getBranch_code());
        employee2.setStatus(request.isStatus());
        employee2.setAddress(request.getAddress());
        employee2.setSecret_key(request.getSecret_key());

        Employee employee3 = new Employee();
        employee3.setName(request.getName());
        employee3.setAge(request.getAge());
        employee3.setBranch_code(request.getBranch_code());
        employee3.setStatus(request.isStatus());
        employee3.setAddress(request.getAddress());
        employee3.setSecret_key(request.getSecret_key());

        Employee employee4 = new Employee();
        employee4.setName(request.getName());
        employee4.setAge(request.getAge());
        employee4.setBranch_code(request.getBranch_code());
        employee4.setStatus(request.isStatus());
        employee4.setAddress(request.getAddress());
        employee4.setSecret_key(request.getSecret_key());

        Employee employee5 = new Employee();
        employee5.setName(request.getName());
        employee5.setAge(request.getAge());
        employee5.setBranch_code(request.getBranch_code());
        employee5.setStatus(request.isStatus());
        employee5.setAddress(request.getAddress());
        employee5.setSecret_key(request.getSecret_key());

        saveEmployee1(employee);

        saveEmployee2(employee2);

        saveEmployee3(employee3);

        saveEmployee4(employee4);

        saveEmployee5(employee5);

        return employeeRepository.save(employee);
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRES_NEW,
            timeout = 30,
            rollbackFor = BadRequestException.class)
    void saveEmployee1(Employee employee) throws BadRequestException {
        employeeRepository.save(employee);
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRES_NEW,
            timeout = 30,
            rollbackFor = BadRequestException.class)
    void saveEmployee2(Employee employee) throws BadRequestException {
        employeeRepository.save(employee);
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRES_NEW,
            timeout = 30,
            rollbackFor = BadRequestException.class)
    void saveEmployee3(Employee employee) throws BadRequestException {
        employeeRepository.save(employee);
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRES_NEW,
            timeout = 30,
            rollbackFor = BadRequestException.class)
    void saveEmployee4(Employee employee) throws BadRequestException {
        throw new BadRequestException("Err");
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRES_NEW,
            timeout = 30,
            rollbackFor = BadRequestException.class)
    void saveEmployee5(Employee employee) throws BadRequestException {
        throw new BadRequestException("Err");
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 30)
    @Override
    public Employee update(long employeeCode, CreateEmployeeRequest request) {
        if (isEmployeeExists(employeeCode)) {
            Employee updatedEmployee = new Employee(
                    employeeCode,
                    request.getName(),
                    request.getAge(),
                    request.getBranch_code(),
                    request.isStatus(),
                    request.getAddress(),
                    request.getSecret_key()
            );

            return employeeRepository.save(updatedEmployee);
        }
        return null;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 30)
    @Override
    public boolean delete(long employeeCode) {
        if (isEmployeeExists(employeeCode)) {
            employeeRepository.deleteById(employeeCode);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 30)
    @Override
    public EmployeeDTO getOne(long employeeCode) {
        Employee e = employeeRepository.getReferenceById(employeeCode);
        return ModelMapper.toEmployeeDTO(e);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByBranchAndGroup(String branchCode) {
        List<Employee> list = employeeRepository.findBranchCodeAndGroup(branchCode);

        List<EmployeeDTO> result = new ArrayList<>();
        for (Employee employee : list) {
            result.add(ModelMapper.toEmployeeDTO(employee));
        }
        return result;
    }
}
