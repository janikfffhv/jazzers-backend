package at.fhv.jazzers.backend.domain.repository;

import at.fhv.jazzers.backend.domain.model.employee.Employee;
import at.fhv.jazzers.backend.domain.model.employee.EmployeeId;

import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> byId(EmployeeId employeeId);
}
