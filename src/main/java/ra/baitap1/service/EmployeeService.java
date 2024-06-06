package ra.baitap1.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.baitap1.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findEmployeeById(Integer emId);
    Employee save(Employee employee);
    Employee update(Employee employee);
    void delete(Integer emId);
    List<Employee> findAllByFullNameContaining(String name);
    List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);
    Page<Employee> findByNameWithPagingAndSorting(String name, Pageable pageable);
    List<Employee> findTop10ByOrderBySalaryDesc(Pageable pageable);

}
