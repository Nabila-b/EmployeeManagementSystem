package ma.ensa.EmployeeManagementSystem.service;

import ma.ensa.EmployeeManagementSystem.model.Employee;
import ma.ensa.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.MessageSource;


import java.util.List;
import java.util.Locale;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MessageSource messageSource;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(messageSource.getMessage("employee.not.found", null, Locale.getDefault())));
    }

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException(messageSource.getMessage("email.already.in.use", null, Locale.getDefault()));
        }
        employeeRepository.save(employee);
        System.out.println(messageSource.getMessage("employee.added.success", null, Locale.getDefault()));
        return employee;
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(messageSource.getMessage("employee.not.found", null, Locale.getDefault())));

        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        employee.setBirthday(employeeDetails.getBirthday());
        employee.setAddress(employeeDetails.getAddress());
        employee.setGender(employeeDetails.getGender());

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException(messageSource.getMessage("employee.not.found", null, Locale.getDefault()));
        }
        employeeRepository.deleteById(id);
    }

    public void deleteEmployee(Employee employee) {
        if (!employeeRepository.existsById(employee.getId())) {
            throw new IllegalArgumentException(messageSource.getMessage("employee.not.found", null, Locale.getDefault()));
        }
        employeeRepository.delete(employee);
    }

    public Long countEmployees() {
        return employeeRepository.count();
    }

    public boolean doesEmployeeExist(Long id) {
        return employeeRepository.existsById(id);
    }

    public boolean doesEmployeeExist(String name) {
        return employeeRepository.existsByName(name);
    }
}