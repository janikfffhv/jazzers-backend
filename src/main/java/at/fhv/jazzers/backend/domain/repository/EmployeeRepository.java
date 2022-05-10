package at.fhv.jazzers.backend.domain.repository;

import at.fhv.jazzers.backend.domain.model.employee.Employee;
import at.fhv.jazzers.backend.domain.model.employee.EmployeeId;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface EmployeeRepository {
    Optional<Employee> byId(EmployeeId employeeId);
}
