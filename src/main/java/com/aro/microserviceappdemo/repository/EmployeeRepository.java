package com.aro.microserviceappdemo.repository;

import com.aro.microserviceappdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
