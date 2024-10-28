package ma.ensa.EmployeeManagementSystem.service;

import ma.ensa.EmployeeManagementSystem.model.Employee;
import ma.ensa.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.getById(id);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.getById(id);
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        employee.setBirthday(employeeDetails.getBirthday());
        employee.setAddress(employeeDetails.getAddress());
        employee.setGender(employeeDetails.getGender());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    public Long countEmployees() {
        return employeeRepository.count();
    }
}
