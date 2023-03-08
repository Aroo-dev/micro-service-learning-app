package com.aro.microserviceappdemo.controller;

import com.aro.microserviceappdemo.dto.ApiResponseDto;
import com.aro.microserviceappdemo.dto.EmployeeDto;
import com.aro.microserviceappdemo.service.EmployeeService;
import com.aro.microserviceappdemo.service.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping()
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        EmployeeDto employee = employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> getEmployeeById(@PathVariable Long id) {
        ApiResponseDto employee = employeeService.findEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> allEmployees = employeeService.findAllEmployees();
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }
}
