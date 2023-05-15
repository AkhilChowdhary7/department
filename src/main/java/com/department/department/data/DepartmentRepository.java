package com.department.department.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long> {

    DepartmentEntity findByName(String name);

    Iterable<DepartmentEntity> findByStateAndCity(String state,String city);

    void deleteByName(String name);

    Iterable<DepartmentEntity> findByCity(String name);

    Iterable<DepartmentEntity> findByState(String state);

    Iterable<DepartmentEntity> findByCountry(String country);

    Iterable<DepartmentEntity> findByZipCode(Integer zipCode);



}
