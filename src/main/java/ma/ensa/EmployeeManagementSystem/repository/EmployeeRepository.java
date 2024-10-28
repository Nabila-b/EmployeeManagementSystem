package ma.ensa.EmployeeManagementSystem.repository;

import ma.ensa.EmployeeManagementSystem.model.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    long count();
    boolean existsById(long id);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}
