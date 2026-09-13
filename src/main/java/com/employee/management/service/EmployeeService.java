package com.employee.management.service;

import com.employee.management.model.Employee;
import com.employee.management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    public EmployeeService(EmployeeRepository repository) { this.repository = repository; }
    public List<Employee> getAll() { return repository.findAll(); }
    public Employee getById(Long id) { return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found")); }
    public Employee create(Employee employee) { return repository.save(employee); }
    public Employee update(Long id, Employee employee) {
        Employee existing = getById(id);
        existing.setName(employee.getName()); existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment()); existing.setDesignation(employee.getDesignation());
        existing.setRole(employee.getRole());
        return repository.save(existing);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
