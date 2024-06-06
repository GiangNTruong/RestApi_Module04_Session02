package ra.baitap1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ra.baitap1.dto.ListEmployeeResponse;
import ra.baitap1.model.Employee;
import ra.baitap1.service.EmployeeService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Integer id){
        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<Employee> add(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.update(employee),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployeeName(@RequestParam String search){
        return new ResponseEntity<>(employeeService.findAllByFullNameContaining(search),HttpStatus.OK);
    }
    @GetMapping("/salary")
    public ResponseEntity<List<Employee>> getEmployeesBySalary(@RequestParam Double minSalary, @RequestParam Double maxSalary) {
        return new ResponseEntity<>(employeeService.findBySalaryBetween(minSalary, maxSalary), HttpStatus.OK);
    }

    @GetMapping("/search/paged")
    public ResponseEntity<ListEmployeeResponse> getEmployeesByNameWithPagingAndSorting(@RequestParam String name) {
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "Salary"));
        Page<Employee> employeePage = employeeService.findByNameWithPagingAndSorting(name, pageable);

        ListEmployeeResponse response = ListEmployeeResponse.builder()
                .content(employeePage.getContent())
                .totalElements(employeePage.getTotalElements())
                .totalPages(employeePage.getTotalPages())
                .size(employeePage.getSize())
                .number(employeePage.getNumber())
                .sort(employeePage.getSort())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/top-salary")
    public ResponseEntity<List<Employee>> getTop10EmployeesBySalary() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "Salary"));
        return new ResponseEntity<>(employeeService.findTop10ByOrderBySalaryDesc(pageable), HttpStatus.OK);
    }
}
