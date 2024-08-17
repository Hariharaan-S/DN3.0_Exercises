package com.employee_management_system.Employee.Management.System.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Getter
@Setter
public class EmployeeDTO {
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private Sort.Direction sort = Sort.Direction.DESC;
    private String sortColumn = "id";

    public Pageable getPagable(EmployeeDTO empdto){
        Integer page = Objects.nonNull(empdto.getPageNo()) ? empdto.getPageNo() : this.getPageNo();
        Integer size = Objects.nonNull(empdto.getPageSize()) ? empdto.getPageSize() : this.getPageSize();
        Sort.Direction sortDirection = Objects.nonNull(empdto.getSort()) ? empdto.getSort() : this.getSort();
        String column = Objects.nonNull(empdto.getSortColumn()) ? empdto.getSortColumn() : this.getSortColumn();
//        return PageRequest.of(page, size);
        return PageRequest.of(page,size,sortDirection,column);
    }
}
