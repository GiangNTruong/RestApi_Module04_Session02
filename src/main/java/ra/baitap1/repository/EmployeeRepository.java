package ra.baitap1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.baitap1.model.Employee;


import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findAllByFullNameContaining(String name);

    @Query("SELECT e FROM Employee e WHERE e.Salary BETWEEN :minSalary AND :maxSalary")
    List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);


    @Query("SELECT e FROM Employee e WHERE e.fullName LIKE %:name% ORDER BY e.Salary DESC")
    Page<Employee> findByNameWithPagingAndSorting(String name, Pageable pageable);


    @Query("SELECT e FROM Employee e ORDER BY e.Salary DESC")
    List<Employee> findTop10ByOrderBySalaryDesc(Pageable pageable);

}
