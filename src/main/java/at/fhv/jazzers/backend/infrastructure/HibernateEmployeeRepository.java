package at.fhv.jazzers.backend.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.domain.model.employee.Employee;
import at.fhv.jazzers.backend.domain.model.employee.EmployeeId;
import at.fhv.jazzers.backend.domain.repository.EmployeeRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.Optional;

@Stateless
public class HibernateEmployeeRepository implements EmployeeRepository {
    private final EntityManager entityManager;

    public HibernateEmployeeRepository() {
        this.entityManager = ServiceRegistry.entityManager();
    }

    @Override
    public Optional<Employee> byId(EmployeeId employeeId) {
        return entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.employeeId = :employeeId", Employee.class)
                .setParameter("employeeId", employeeId)
                .getResultList()
                .stream()
                .findFirst();
    }
}
