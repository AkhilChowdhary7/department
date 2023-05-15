package com.department.department.service;

import com.department.department.exception.NotFoundException;
import com.department.department.shared.DepartmentDto;
import jakarta.xml.bind.ValidationException;

import java.util.List;
public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto DepartmentDetails);

    List<DepartmentDto> getAllDepartments() throws NotFoundException, ValidationException;

    DepartmentDto getDepartmentById(long id);

    DepartmentDto getDepartmentByName(String name);

    boolean deleteDepartmentByName(String name);

    List<DepartmentDto> getDepartmentByStateAndCity(String state,String city);

    List<DepartmentDto> getDepartmentByState(String state);

    List<DepartmentDto> getDepartmentByCountry(String country);

    List<DepartmentDto> getDepartmentByCity(String city);

    List<DepartmentDto> getDepartmentByZipCode(Integer zipcode);





}
