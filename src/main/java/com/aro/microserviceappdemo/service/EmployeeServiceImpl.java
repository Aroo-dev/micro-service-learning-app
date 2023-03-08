package com.aro.microserviceappdemo.service;

import com.aro.microserviceappdemo.dto.ApiResponseDto;
import com.aro.microserviceappdemo.dto.DepartmentDto;
import com.aro.microserviceappdemo.dto.EmployeeDto;
import com.aro.microserviceappdemo.entity.Employee;
import com.aro.microserviceappdemo.exceptions.ResourceNotFoundException;
import com.aro.microserviceappdemo.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    private final ApiClient apiClient;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);

        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee,EmployeeDto.class);

    }
    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiResponseDto findEmployee(Long id) {

        Employee employee = employeeRepository.findById(id).get();
        DepartmentDto departmentByDepartmentCode = apiClient.getDepartmentByDepartmentCode(employee.getDepartmentCode());
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        ApiResponseDto apiResponseDto = new ApiResponseDto(employeeDto,departmentByDepartmentCode);

        return apiResponseDto;


        }


    @Override
    public List<EmployeeDto> findAllEmployees() {
        List<Employee> listOfAllEmployees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = listOfAllEmployees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toUnmodifiableList());
        return employeeDtoList;

    }

    public ApiResponseDto getDefaultDepartment(Long id){


            Employee employee = employeeRepository.findById(id).get();

            DepartmentDto departmentDto = new DepartmentDto();
            EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

            ApiResponseDto apiResponseDto = new ApiResponseDto(employeeDto,departmentDto);

            return apiResponseDto;




    }
}
