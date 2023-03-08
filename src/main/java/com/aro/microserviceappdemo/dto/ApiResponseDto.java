package com.aro.microserviceappdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ApiResponseDto {

    private EmployeeDto employeeDto;
    private DepartmentDto departmentDto;
}
