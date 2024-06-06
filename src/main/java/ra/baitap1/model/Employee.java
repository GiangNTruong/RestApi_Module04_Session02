package ra.baitap1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer EmpId;
    @Column(length = 50)
    private String fullName;
    private Boolean Gender;
    private Date Birthday;
    @Column(length = 50)
    private String Address;
    private String Company;
    private String Department;
    private Double Salary;

}
