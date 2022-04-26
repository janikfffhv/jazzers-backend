package at.fhv.jazzers.backend.unit.infrastructure;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.backend.domain.model.employee.Employee;
import at.fhv.jazzers.backend.domain.model.employee.EmployeeId;
import at.fhv.jazzers.backend.domain.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class HibernateEmployeeRepositoryTests {
    private EntityManager entityManager;
    private final EmployeeRepository employeeRepository = ServiceRegistry.employeeRepository();

    @BeforeEach
    void setUp() {
        this.entityManager = ServiceRegistry.entityManager();
    }

    @Test
    public void when_searching_employees_by_id_then_matching_employee() {
        // Generate Test Data
        EmployeeId barbarosId = new EmployeeId("bte3268");
        EmployeeId christophId = new EmployeeId("cpe2877");
        EmployeeId eliasId = new EmployeeId("eha7244");

        Employee barbaros = new Employee(barbarosId, List.of(), List.of());
        Employee christoph = new Employee(christophId, List.of(), List.of());
        Employee elias = new Employee(eliasId, List.of(), List.of());

        List<Employee> employees = List.of(barbaros, christoph, elias);

        // Flush Data (load data into database but do not persist)
        entityManager.getTransaction().begin();
        employees.forEach(entityManager::persist);
        entityManager.flush();
        Optional<Employee> actual = employeeRepository.byId(eliasId);
        entityManager.getTransaction().rollback();

        // Assertions
        assertNotNull(actual);
        actual.ifPresent(employee -> assertEquals(eliasId, employee.employeeId()));
    }

    @Test
    public void when_searching_employees_by_invalid_id_then_optional_empty() {
        // Generate Test Data
        EmployeeId barbarosId = new EmployeeId("bte3268");
        EmployeeId christophId = new EmployeeId("cpe2877");
        EmployeeId eliasId = new EmployeeId("eha7244");

        Employee barbaros = new Employee(barbarosId, List.of(), List.of());
        Employee christoph = new Employee(christophId, List.of(), List.of());
        Employee elias = new Employee(eliasId, List.of(), List.of());

        List<Employee> employees = List.of(barbaros, christoph, elias);

        // Flush Data (load data into database but do not persist)
        entityManager.getTransaction().begin();
        employees.forEach(entityManager::persist);
        entityManager.flush();
        Optional<Employee> actual = employeeRepository.byId(new EmployeeId("BlaBla"));
        entityManager.getTransaction().rollback();

        // Assertions
        assertEquals(Optional.empty(), actual);
    }
}
