package com.department.department;

import com.department.department.data.DepartmentEntity;
import com.department.department.data.DepartmentRepository;
import com.department.department.service.DepartmentServiceImpl;
import com.department.department.shared.DepartmentDto;
import com.department.department.shared.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {
    @InjectMocks
    DepartmentServiceImpl departmentService;

    @Mock
    DepartmentRepository departmentRepository;

    @Mock
    Utils utils;

    @Test
    public void getAllDepartmentsTest(){
        DepartmentDto departmentDto1=new DepartmentDto(1,"name","city","state","country",56789);
        DepartmentDto departmentDto=new DepartmentDto(2,"name1","city2","state3","country4",56589);

        List<DepartmentDto> departmentDtoList= new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        DepartmentEntity departmentEntity=new DepartmentEntity(1,"name","city","state","country",56789);
        DepartmentEntity departmentEntity1=new DepartmentEntity(2,"name1","city2","state3","country4",56589);

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity, departmentEntity1);

        when(departmentRepository.findAll()).thenReturn(departmentEntities);
        when(departmentService.getAllDepartments()).thenReturn(departmentDtoList);
        when(utils.getDepartmentDtoList(departmentEntities)).thenReturn(departmentDtoList);

        List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();

        assertThat(departmentDtos.get(0).getName()).isEqualTo(departmentDto1.getName());
        assertThat(departmentDtos.get(0).getCity()).isEqualTo(departmentDto1.getCity());
        assertThat(departmentDtos.get(0).getState()).isEqualTo(departmentDto1.getState());
        assertThat(departmentDtos.get(0).getCountry()).isEqualTo(departmentDto1.getCountry());
        assertThat(departmentDtos.get(0).getZipCode()).isEqualTo(departmentDto1.getZipCode());


    }

    @Test
    public void getDepartmentByNameTest(){
        DepartmentEntity departmentEntity=new DepartmentEntity(1,"name","city","state","country",56789);
        String name = "name";

        when(departmentRepository.findByName(name)).thenReturn(departmentEntity);

        DepartmentDto departmentDto = departmentService.getDepartmentByName(name);

        assertThat(departmentDto.getName()).isEqualTo(departmentEntity.getName());
        assertThat(departmentDto.getCity()).isEqualTo(departmentEntity.getCity());
        assertThat(departmentDto.getState()).isEqualTo(departmentEntity.getState());
        assertThat(departmentDto.getCountry()).isEqualTo(departmentEntity.getCountry());
        assertThat(departmentDto.getZipCode()).isEqualTo(departmentEntity.getZipCode());

    }

    @Test
    public void getDepartmentByNameNullTest(){

        DepartmentEntity departmentEntity=null;

        String name = "name";

        when(departmentRepository.findByName(name)).thenReturn(departmentEntity);

        DepartmentDto departmentDto= departmentService.getDepartmentByName(name);

        assertThat(departmentDto).isNull();
    }

    @Test
    public void getDepartmentByStateTest(){
        DepartmentDto departmentDto1=new DepartmentDto(1,"name","city","state","country",56789);
        DepartmentDto departmentDto=new DepartmentDto(2,"name1","city2","state3","country4",56589);

        List<DepartmentDto> departmentDtoList= new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        DepartmentEntity departmentEntity=new DepartmentEntity(1,"name","city","state","country",56789);
        DepartmentEntity departmentEntity1=new DepartmentEntity(2,"name1","city2","state3","country4",56589);

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity, departmentEntity1);

        String state = "state";

        when(departmentRepository.findByState(state)).thenReturn(departmentEntities);
        when(departmentService.getDepartmentByState(state)).thenReturn(departmentDtoList);
        when(utils.getDepartmentDtoList(departmentEntities)).thenReturn(departmentDtoList);

        List<DepartmentDto> departmentDtos = departmentService.getDepartmentByState(state);

        assertThat(departmentDtos.get(0).getName()).isEqualTo(departmentDto1.getName());
        assertThat(departmentDtos.get(0).getCity()).isEqualTo(departmentDto1.getCity());
        assertThat(departmentDtos.get(0).getState()).isEqualTo(departmentDto1.getState());
        assertThat(departmentDtos.get(0).getCountry()).isEqualTo(departmentDto1.getCountry());
        assertThat(departmentDtos.get(0).getZipCode()).isEqualTo(departmentDto1.getZipCode());

    }

    @Test
    public void getDepartmentByCityTest(){
        DepartmentDto departmentDto1=new DepartmentDto(1,"name","city","state","country",56789);
        DepartmentDto departmentDto=new DepartmentDto(2,"name1","city2","state3","country4",56589);

        List<DepartmentDto> departmentDtoList= new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        DepartmentEntity departmentEntity=new DepartmentEntity(1,"name","city","state","country",56789);
        DepartmentEntity departmentEntity1=new DepartmentEntity(2,"name1","city2","state3","country4",56589);

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity, departmentEntity1);

        String city = "city";

        when(departmentRepository.findByCity(city)).thenReturn(departmentEntities);
        when(departmentService.getDepartmentByCity(city)).thenReturn(departmentDtoList);
        when(utils.getDepartmentDtoList(departmentEntities)).thenReturn(departmentDtoList);

        List<DepartmentDto> departmentDtos = departmentService.getDepartmentByCity(city);

        assertThat(departmentDtos.get(0).getName()).isEqualTo(departmentDto1.getName());
        assertThat(departmentDtos.get(0).getCity()).isEqualTo(departmentDto1.getCity());
        assertThat(departmentDtos.get(0).getState()).isEqualTo(departmentDto1.getState());
        assertThat(departmentDtos.get(0).getCountry()).isEqualTo(departmentDto1.getCountry());
        assertThat(departmentDtos.get(0).getZipCode()).isEqualTo(departmentDto1.getZipCode());

    }

    @Test
    public void getDepartmentByStateAndCityTest(){
        DepartmentDto departmentDto1=new DepartmentDto(1,"name","city","state","country",56789);
        DepartmentDto departmentDto=new DepartmentDto(2,"name1","city2","state3","country4",56589);

        List<DepartmentDto> departmentDtoList= new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        DepartmentEntity departmentEntity=new DepartmentEntity(1,"name","city","state","country",56789);
        DepartmentEntity departmentEntity1=new DepartmentEntity(2,"name1","city2","state3","country4",56589);

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity, departmentEntity1);

        String city = "city";
        String state = "state";

        when(departmentRepository.findByStateAndCity(state,city)).thenReturn(departmentEntities);
        when(departmentService.getDepartmentByStateAndCity(state,city)).thenReturn(departmentDtoList);
        when(utils.getDepartmentDtoList(departmentEntities)).thenReturn(departmentDtoList);

        List<DepartmentDto> departmentDtos = departmentService.getDepartmentByStateAndCity(state,city);

        assertThat(departmentDtos.get(0).getName()).isEqualTo(departmentDto1.getName());
        assertThat(departmentDtos.get(0).getCity()).isEqualTo(departmentDto1.getCity());
        assertThat(departmentDtos.get(0).getState()).isEqualTo(departmentDto1.getState());
        assertThat(departmentDtos.get(0).getCountry()).isEqualTo(departmentDto1.getCountry());
        assertThat(departmentDtos.get(0).getZipCode()).isEqualTo(departmentDto1.getZipCode());

    }

    @Test
    public  void getDepartmentByIdTest(){
        Optional<DepartmentEntity> departmentEntity= Optional.of(new DepartmentEntity(1,"name","city","state","country",56789));

        when(departmentRepository.findById(1L)).thenReturn(departmentEntity);

        DepartmentDto departmentDto1 = departmentService.getDepartmentById(1L);

        assertThat(departmentDto1.getName()).isEqualTo(departmentEntity.get().getName());
        assertThat(departmentDto1.getCity()).isEqualTo(departmentEntity.get().getCity());
        assertThat(departmentDto1.getState()).isEqualTo(departmentEntity.get().getState());
        assertThat(departmentDto1.getCountry()).isEqualTo(departmentEntity.get().getCountry());
        assertThat(departmentDto1.getZipCode()).isEqualTo(departmentEntity.get().getZipCode());

    }

    @Test
    public void createDepartmentTest(){
        DepartmentDto departmentDto=new DepartmentDto(1,"name","city","state","country",56789);

        DepartmentDto departmentDto1= departmentService.createDepartment(departmentDto);

        assertThat(departmentDto.getName()).isEqualTo(departmentDto.getName());
        assertThat(departmentDto.getCity()).isEqualTo(departmentDto.getCity());
        assertThat(departmentDto.getState()).isEqualTo(departmentDto.getState());
        assertThat(departmentDto.getCountry()).isEqualTo(departmentDto.getCountry());
        assertThat(departmentDto.getZipCode()).isEqualTo(departmentDto.getZipCode());


    }
    @Test
    public void deleteDepartmentByName(){
        String name = "name";
        doNothing().when(departmentRepository).deleteByName(name);
        boolean res = departmentService.deleteDepartmentByName(name);
        assertThat(res).isEqualTo(true);

    }

    @Test
    public void deleteDepartmentByNameExceptionTest(){
        String name="name";
        doThrow(RuntimeException.class).when(departmentRepository).deleteByName(name);
        boolean res=departmentService.deleteDepartmentByName(name);
        assertThat(res).isEqualTo(false);
    }

    @Test
    public void getDepartmentByCountryTest(){
        DepartmentDto departmentDto1=new DepartmentDto(1,"name","city","state","country",56789);
        DepartmentDto departmentDto=new DepartmentDto(2,"name1","city2","state3","country4",56589);

        List<DepartmentDto> departmentDtoList= new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        DepartmentEntity departmentEntity=new DepartmentEntity(1,"name","city","state","country",56789);
        DepartmentEntity departmentEntity1=new DepartmentEntity(2,"name1","city2","state3","country4",56589);

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity, departmentEntity1);

        String country = "country";

        when(departmentRepository.findByCountry(country)).thenReturn(departmentEntities);
        when(departmentService.getDepartmentByCountry(country)).thenReturn(departmentDtoList);
        when(utils.getDepartmentDtoList(departmentEntities)).thenReturn(departmentDtoList);

        List<DepartmentDto> departmentDtos = departmentService.getDepartmentByCountry(country);

        assertThat(departmentDtos.get(0).getName()).isEqualTo(departmentDto1.getName());
        assertThat(departmentDtos.get(0).getCity()).isEqualTo(departmentDto1.getCity());
        assertThat(departmentDtos.get(0).getState()).isEqualTo(departmentDto1.getState());
        assertThat(departmentDtos.get(0).getCountry()).isEqualTo(departmentDto1.getCountry());
        assertThat(departmentDtos.get(0).getZipCode()).isEqualTo(departmentDto1.getZipCode());


    }

    @Test
    public void getDepartmentByZipCodeTest(){
        DepartmentDto departmentDto1=new DepartmentDto(1,"name","city","state","country",56789);
        DepartmentDto departmentDto=new DepartmentDto(2,"name1","city2","state3","country4",56589);

        List<DepartmentDto> departmentDtoList= new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        DepartmentEntity departmentEntity=new DepartmentEntity(1,"name","city","state","country",56789);
        DepartmentEntity departmentEntity1=new DepartmentEntity(2,"name1","city2","state3","country4",56589);

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity, departmentEntity1);

        int zipcode = 56789;

        when(departmentRepository.findByZipCode(56789)).thenReturn(departmentEntities);
        when(departmentService.getDepartmentByZipCode(56789)).thenReturn(departmentDtoList);
        when(utils.getDepartmentDtoList(departmentEntities)).thenReturn(departmentDtoList);

        List<DepartmentDto> departmentDtos = departmentService.getDepartmentByZipCode(zipcode);

        assertThat(departmentDtos.get(0).getName()).isEqualTo(departmentDto1.getName());
        assertThat(departmentDtos.get(0).getCity()).isEqualTo(departmentDto1.getCity());
        assertThat(departmentDtos.get(0).getState()).isEqualTo(departmentDto1.getState());
        assertThat(departmentDtos.get(0).getCountry()).isEqualTo(departmentDto1.getCountry());
        assertThat(departmentDtos.get(0).getZipCode()).isEqualTo(departmentDto1.getZipCode());

    }





}
