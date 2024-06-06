package ra.baitap1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.baitap1.model.Employee;
import ra.baitap1.repository.EmployeeRepository;
import ra.baitap1.service.EmployeeService;


import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private  final  EmployeeRepository employeeRepository;
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Integer emId) {
        return employeeRepository.findById(emId).orElseThrow(()->new NoSuchElementException("Khong co id ton tai"));
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        employeeRepository.findById(employee.getEmpId()).orElseThrow(()->new NoSuchElementException("Khong co id ton tai"));
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Integer emId) {
        employeeRepository.deleteById(emId);

    }

    @Override
    public List<Employee> findAllByFullNameContaining(String name) {
        return employeeRepository.findAllByFullNameContaining(name);
    }

    @Override
    public List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary) {
        return employeeRepository.findBySalaryBetween(minSalary,maxSalary);
    }

    @Override
    public Page<Employee> findByNameWithPagingAndSorting(String name, Pageable pageable) {
        return employeeRepository.findByNameWithPagingAndSorting(name, pageable);
    }

    @Override
    public List<Employee> findTop10ByOrderBySalaryDesc(Pageable pageable) {
        return employeeRepository.findTop10ByOrderBySalaryDesc(pageable);
    }
}
