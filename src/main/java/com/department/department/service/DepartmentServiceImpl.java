package com.department.department.service;
import com.department.department.data.DepartmentEntity;
import com.department.department.data.DepartmentRepository;
import com.department.department.exception.NotFoundException;
//import com.department.department.model.DepartmentRequestModel;
import com.department.department.shared.DepartmentDto;
import com.department.department.shared.Utils;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;
    Utils utils;


    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository,Utils utils)
    {
        this.departmentRepository=departmentRepository;
        this.utils=utils;
    }


    @Override
    public List<DepartmentDto> getAllDepartments() {
        Iterable<DepartmentEntity> departments= departmentRepository.findAll();


        return utils.getDepartmentDtoList(departments);
    }

    @Override
    public DepartmentDto getDepartmentById(long id){
        Optional<DepartmentEntity> departmentEntity= departmentRepository.findById(id);
        return  new ModelMapper().map(departmentEntity, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentByName(String name){
        DepartmentEntity departments= departmentRepository.findByName(name);
//        DepartmentEntity departments=departmentRepository.findByName(name);

//        return utils.getDepartmentDto(departments);
        if(departments == null){
            return null;
        }else{
            return new ModelMapper().map(departments, DepartmentDto.class);
        }
        //return department;
    }

    @Override
    public boolean deleteDepartmentByName(String name){
        try{
            departmentRepository.deleteByName(name);
            return true;

        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public List<DepartmentDto> getDepartmentByCity(String city){
        Iterable<DepartmentEntity> departments = departmentRepository.findByCity(city);

//        List<DepartmentDto> allDepartments= new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();
//        long count = StreamSupport.stream(departments.spliterator(),false).count();
//        if(count > 0){
//            departments.forEach(departmentEntity -> {
//                    DepartmentDto departmentDto = modelMapper.map(departmentEntity,DepartmentDto.class );
//                    allDepartments.add(departmentDto);
//            });
//        }

        return utils.getDepartmentDtoList(departments);

    }

    @Override
    public List<DepartmentDto> getDepartmentByStateAndCity(String state,String city){
        Iterable<DepartmentEntity> departments = departmentRepository.findByStateAndCity(state,city);

        return utils.getDepartmentDtoList(departments);

    }

    @Override
    public List<DepartmentDto> getDepartmentByState(String state){
        Iterable<DepartmentEntity> departments = departmentRepository.findByState(state);

//        List<DepartmentDto> allDepartments= new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();
//        long count = StreamSupport.stream(departments.spliterator(),false).count();
//        if(count > 0){
//            departments.forEach(departmentEntity -> {
//                DepartmentDto departmentDto = modelMapper.map(departmentEntity,DepartmentDto.class );
//                allDepartments.add(departmentDto);
//            });
//        }

        return utils.getDepartmentDtoList(departments);
    }

    @Override
    public List<DepartmentDto> getDepartmentByCountry(String country){
        Iterable<DepartmentEntity> departments = departmentRepository.findByCountry(country);

//        List<DepartmentDto> allDepartments= new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();
//        long count = StreamSupport.stream(departments.spliterator(),false).count();
//        if(count > 0){
//            departments.forEach(departmentEntity -> {
//                DepartmentDto departmentDto = modelMapper.map(departmentEntity,DepartmentDto.class );
//                allDepartments.add(departmentDto);
//            });
//        }

        return utils.getDepartmentDtoList(departments);
    }

    @Override
    public List<DepartmentDto> getDepartmentByZipCode(Integer zipcode){
        Iterable<DepartmentEntity> departments = departmentRepository.findByZipCode(zipcode);

//        List<DepartmentDto> allDepartments= new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();
//        long count = StreamSupport.stream(departments.spliterator(),false).count();
//        if(count > 0){
//            departments.forEach(departmentEntity -> {
//                DepartmentDto departmentDto = modelMapper.map(departmentEntity,DepartmentDto.class );
//                allDepartments.add(departmentDto);
//            });
//        }

        return utils.getDepartmentDtoList(departments);
    }




    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDetails){
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        DepartmentEntity departmentEntity=modelMapper. map(departmentDetails, DepartmentEntity.class);

        departmentRepository.save(departmentEntity);
        System.out.println(departmentEntity.getZipCode());
        return modelMapper.map(departmentEntity, DepartmentDto.class);

    }


}
