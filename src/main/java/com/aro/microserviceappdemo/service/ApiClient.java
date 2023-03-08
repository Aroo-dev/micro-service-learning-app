package com.aro.microserviceappdemo.service;

import com.aro.microserviceappdemo.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface ApiClient {
    @GetMapping("/api/v1/departments/{department-code}")
    public DepartmentDto getDepartmentByDepartmentCode(@PathVariable("department-code") String departmentCode);
}
