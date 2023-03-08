package com.aro.microserviceappdemo.service;

import com.aro.microserviceappdemo.dto.ApiResponseDto;
import com.aro.microserviceappdemo.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    ApiResponseDto findEmployee(Long id);

    List<EmployeeDto> findAllEmployees();
}
