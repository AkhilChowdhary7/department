package com.department.department.service;
import com.department.department.data.DepartmentEntity;
import com.department.department.data.DepartmentRepository;
import com.department.department.shared.DepartmentDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository)
    {
        this.departmentRepository=departmentRepository;
    }


    @Override
    public List<DepartmentDto> getAllDepartments(){
        List<DepartmentDto> allDepartments = new ArrayList<>();
        Iterable<DepartmentEntity> departments= departmentRepository.findAll();
        ModelMapper modelMapper= new ModelMapper();
        long count= StreamSupport.stream(departments.spliterator(),false).count();
        if(count > 0){
            departments.forEach(departmentEntity -> {
                DepartmentDto departmentDto= modelMapper.map(departmentEntity, DepartmentDto.class);
                allDepartments.add(departmentDto);
            });
        }

        return allDepartments;

    }

    @Override
    public DepartmentDto getDepartmentById(long id){
        Optional<DepartmentEntity> departmentEntity= departmentRepository.findById(id);
        return  new ModelMapper().map(departmentEntity, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentByName(String name){
        DepartmentEntity department= departmentRepository.findByName(name);
        if (department==null){
            return  null;
        }else{
            return new ModelMapper().map(department,DepartmentDto.class);
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
        List<DepartmentDto> allDepartments= new ArrayList<>();
        Iterable<DepartmentEntity> departments = departmentRepository.findByCity(city);
        ModelMapper modelMapper = new ModelMapper();
        long count = StreamSupport.stream(departments.spliterator(),false).count();
        if(count > 0){
            departments.forEach(departmentEntity -> {
                    DepartmentDto departmentDto = modelMapper.map(departmentEntity,DepartmentDto.class );
                    allDepartments.add(departmentDto);
            });
        }

        return allDepartments;
    }

    @Override
    public List<DepartmentDto> getDepartmentByState(String state){
        List<DepartmentDto> allDepartments= new ArrayList<>();
        Iterable<DepartmentEntity> departments = departmentRepository.findByState(state);
        ModelMapper modelMapper = new ModelMapper();
        long count = StreamSupport.stream(departments.spliterator(),false).count();
        if(count > 0){
            departments.forEach(departmentEntity -> {
                DepartmentDto departmentDto = modelMapper.map(departmentEntity,DepartmentDto.class );
                allDepartments.add(departmentDto);
            });
        }

        return allDepartments;
    }

    @Override
    public List<DepartmentDto> getDepartmentByCountry(String country){
        List<DepartmentDto> allDepartments= new ArrayList<>();
        Iterable<DepartmentEntity> departments = departmentRepository.findByCountry(country);
        ModelMapper modelMapper = new ModelMapper();
        long count = StreamSupport.stream(departments.spliterator(),false).count();
        if(count > 0){
            departments.forEach(departmentEntity -> {
                DepartmentDto departmentDto = modelMapper.map(departmentEntity,DepartmentDto.class );
                allDepartments.add(departmentDto);
            });
        }

        return allDepartments;
    }

    @Override
    public List<DepartmentDto> getDepartmentByZipCode(Integer zipcode){
        List<DepartmentDto> allDepartments= new ArrayList<>();
        Iterable<DepartmentEntity> departments = departmentRepository.findByZipCode(zipcode);
        ModelMapper modelMapper = new ModelMapper();
        long count = StreamSupport.stream(departments.spliterator(),false).count();
        if(count > 0){
            departments.forEach(departmentEntity -> {
                DepartmentDto departmentDto = modelMapper.map(departmentEntity,DepartmentDto.class );
                allDepartments.add(departmentDto);
            });
        }

        return allDepartments;
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
