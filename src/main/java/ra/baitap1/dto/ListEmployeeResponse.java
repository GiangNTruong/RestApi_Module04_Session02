package ra.baitap1.dto;

import lombok.*;
import org.springframework.data.domain.Sort;
import ra.baitap1.model.Employee;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ListEmployeeResponse {
    private List<Employee> content;
    private long totalElements;
    private int totalPages;
    private int size;
    private int number;
    private Sort sort;
}
