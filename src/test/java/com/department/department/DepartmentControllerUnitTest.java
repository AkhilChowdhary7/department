package com.department.department;
import com.department.department.data.DepartmentEntity;
import com.department.department.exception.NotFoundException;
import com.department.department.model.DepartmentRequestModel;
import com.department.department.model.DepartmentResponseModel;
import com.department.department.service.DepartmentServiceImpl;
import com.department.department.shared.DepartmentDto;
import com.department.department.shared.Utils;


import com.department.department.controller.DepartmentController;
import jakarta.xml.bind.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentControllerUnitTest {

    @InjectMocks
    DepartmentController departmentController;

    @Mock
    Utils utils;

    @Mock
    DepartmentServiceImpl departmentService;

    @Test
    public void getAllDepartmentsTest() throws NotFoundException, ValidationException {
        DepartmentDto departmentDto1=new DepartmentDto(1,"name","city","state","country",56789);
        DepartmentDto departmentDto=new DepartmentDto(2,"name1","city2","state3","country4",56589);

        List<DepartmentDto> departmentDtoList= new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        List<DepartmentResponseModel> departmentList = new ArrayList<>();
        departmentDtoList.forEach(departmentDtoTemp ->  {
            DepartmentResponseModel departmentResponseModel = new ModelMapper().map(departmentDtoTemp, DepartmentResponseModel.class);
            departmentList.add(departmentResponseModel);
        });

        when(departmentService.getAllDepartments()).thenReturn(departmentDtoList);
        when(utils.getDepartmentResponseModelList(departmentDtoList)).thenReturn(departmentList);

        ResponseEntity<List<DepartmentResponseModel>> responseEntity = departmentController.getAllDepartments();
        List<DepartmentResponseModel> departmentResponseModelList = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode().toString()).isEqualTo("200 OK");
        assert departmentResponseModelList != null;
        assertThat(departmentResponseModelList.get(0).getZipCode()).isEqualTo(56789);
        assertThat(departmentResponseModelList.get(1).getZipCode()).isEqualTo(56589);

    }

    @Test
    public void getDepartmentByNameTest() throws NotFoundException{
        DepartmentDto departmentDto1=new DepartmentDto(1,"name","city","state","country",56789);
        DepartmentResponseModel departmentResponseModel = new ModelMapper().map(departmentDto1, DepartmentResponseModel.class);

        String name = "name";
        when(departmentService.getDepartmentByName(name)).thenReturn(departmentDto1);
        when(utils.getDepartmentResponseModel(departmentDto1)).thenReturn(departmentResponseModel);

        ResponseEntity<DepartmentResponseModel> departmentResponseModelResponseEntity  = departmentController.getDepartmentByName(name);

        DepartmentResponseModel departmentResponseModel1 = departmentResponseModelResponseEntity.getBody();
        assertThat(departmentResponseModelResponseEntity.getStatusCode().toString()).isEqualTo("200 OK");
        assert departmentResponseModel1 != null;
        assertThat(departmentResponseModel1.getName()).isEqualTo(name);

    }

    @Test
    public void searchDepartmentTest() throws NotFoundException{
        DepartmentDto departmentDto1=new DepartmentDto(1,"name","city","state","country",56789);
        DepartmentDto departmentDto=new DepartmentDto(2,"name1","city2","state3","country4",56589);

        List<DepartmentDto> departmentDtoList= new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        String city = "city";
        String state = "state";
        long zipcode=56789;

        List<DepartmentResponseModel> departmentList = new ArrayList<>();
        departmentDtoList.forEach(departmentDtoTemp ->  {
            DepartmentResponseModel departmentResponseModel = new ModelMapper().map(departmentDtoTemp, DepartmentResponseModel.class);
            departmentList.add(departmentResponseModel);
        });

        when(departmentService.getDepartmentByStateAndCity(state,city)).thenReturn(departmentDtoList);
        when(utils.getDepartmentResponseModelList(departmentDtoList)).thenReturn(departmentList);

        ResponseEntity<List<DepartmentResponseModel>> responseEntity = departmentController.searchDepartments(state, city);

        List<DepartmentResponseModel> departmentResponseModelList = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode().toString()).isEqualTo("200 OK");
        assert departmentResponseModelList != null;
        assertThat(departmentResponseModelList.get(0).getZipCode()).isEqualTo(56789);
        assertThat(departmentResponseModelList.get(1).getZipCode()).isEqualTo(56589);

    }

    @Test
    public void searchDepartmentStateTest() throws NotFoundException{
        DepartmentDto departmentDto1=new DepartmentDto(1,"name","city","state","country",56789);
        DepartmentDto departmentDto=new DepartmentDto(2,"name1","city2","state3","country4",56589);

        List<DepartmentDto> departmentDtoList= new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        String city =null;
        String state = "state";
//        long zipcode= 56789;

        List<DepartmentResponseModel> departmentList = new ArrayList<>();
        departmentDtoList.forEach(departmentDtoTemp ->  {
            DepartmentResponseModel departmentResponseModel = new ModelMapper().map(departmentDtoTemp, DepartmentResponseModel.class);
            departmentList.add(departmentResponseModel);
        });

        when(departmentService.getDepartmentByStateAndCity(state,city)).thenReturn(departmentDtoList);
        when(utils.getDepartmentResponseModelList(departmentDtoList)).thenReturn(departmentList);

        ResponseEntity<List<DepartmentResponseModel>> responseEntity = departmentController.searchDepartments(state,city);

        List<DepartmentResponseModel> departmentResponseModelList = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode().toString()).isEqualTo("200 OK");
        assert departmentResponseModelList != null;
        assertThat(departmentResponseModelList.get(0).getZipCode()).isEqualTo(56789);
        assertThat(departmentResponseModelList.get(1).getZipCode()).isEqualTo(56589);

    }

    @Test
    public void searchDepartmentCityTest() throws NotFoundException{
        DepartmentDto departmentDto1=new DepartmentDto(1,"name","city","state","country",56789);
        DepartmentDto departmentDto=new DepartmentDto(2,"name1","city2","state3","country4",56589);

        List<DepartmentDto> departmentDtoList= new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        String city ="city";
        String state = null;
//        int zipcode=56789;

        List<DepartmentResponseModel> departmentList = new ArrayList<>();
        departmentDtoList.forEach(departmentDtoTemp ->  {
            DepartmentResponseModel departmentResponseModel = new ModelMapper().map(departmentDtoTemp, DepartmentResponseModel.class);
            departmentList.add(departmentResponseModel);
        });

        when(departmentService.getDepartmentByCity(city)).thenReturn(departmentDtoList);
        when(utils.getDepartmentResponseModelList(departmentDtoList)).thenReturn(departmentList);

        ResponseEntity<List<DepartmentResponseModel>> responseEntity = departmentController.searchDepartments(state,city);

        List<DepartmentResponseModel> departmentResponseModelList = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode().toString()).isEqualTo("200 OK");
        assert departmentResponseModelList != null;
        assertThat(departmentResponseModelList.get(0).getZipCode()).isEqualTo(56789);
        assertThat(departmentResponseModelList.get(1).getZipCode()).isEqualTo(56589);

    }

    @Test
    public void searchDepartmentNoNameTest() throws NotFoundException {
        List<DepartmentDto> departmentDtoList = null;
        String city = null;
        String state = null;
//        long zipcode = 0;

        when(utils.getDepartmentResponseModelList(departmentDtoList)).thenThrow(NotFoundException.class);

        try {
            departmentController.searchDepartments(city, state);
        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(NotFoundException.class);
        }
    }
    @Test
    public void createDepartmentTest() throws NotFoundException{
        DepartmentRequestModel departmentRequestModel = new DepartmentRequestModel("name","city","state","country",56789);
        DepartmentDto departmentDto=new DepartmentDto(1,"name","city","state","country",56789);

        DepartmentResponseModel departmentResponseModel = new ModelMapper().map(departmentDto, DepartmentResponseModel.class);

        when(departmentService.getDepartmentByName(departmentRequestModel.getName())).thenReturn(null);
        when(utils.getDepartmentDto(departmentRequestModel)).thenReturn(departmentDto);
        when(departmentService.createDepartment(departmentDto)).thenReturn(departmentDto);
        when(utils.getDepartmentResponseModel(departmentDto)).thenReturn(departmentResponseModel);

        ResponseEntity<DepartmentResponseModel> responseEntity= departmentController.createDepartment(departmentRequestModel);

        DepartmentResponseModel departmentResponseModel1 = responseEntity.getBody();
        assert departmentResponseModel1 != null;

        assertThat(departmentResponseModel1.getName()).isEqualTo(departmentRequestModel.getName());

    }

    @Test
    public void updateDepartmentTest() throws NotFoundException{
        DepartmentRequestModel departmentRequestModel = new DepartmentRequestModel("name","city","state","country",56789);
        DepartmentDto departmentDto=new DepartmentDto(1,"name","city","state","country",56789);

        DepartmentResponseModel departmentResponseModel = new ModelMapper().map(departmentDto, DepartmentResponseModel.class);

        when(departmentService.getDepartmentByName(departmentRequestModel.getName())).thenReturn(departmentDto);
        when(utils.getDepartmentDto(departmentRequestModel)).thenReturn(departmentDto);
        when(departmentService.createDepartment(departmentDto)).thenReturn(departmentDto);
        when(utils.getDepartmentResponseModel(departmentDto)).thenReturn(departmentResponseModel);

        ResponseEntity<DepartmentResponseModel> responseEntity= departmentController.updateDepartment(departmentRequestModel);

        DepartmentResponseModel departmentResponseModel1 = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode().toString()).isEqualTo("200 OK");
        assert departmentResponseModel1 != null;
        assertThat(departmentResponseModel1.getName()).isEqualTo(departmentRequestModel.getName());

    }

    @Test
    public void deleteDepartmentTest(){
        DepartmentDto departmentDto=new DepartmentDto(1,"name","city","state","country",56789);
        String name="name";

        when(departmentService.getDepartmentByName(name)).thenReturn(departmentDto);
        when(departmentService.deleteDepartmentByName(name)).thenReturn(true);
        ResponseEntity<Void> responseEntity = departmentController.delete(name);
        assertThat(responseEntity.getStatusCode().toString()).isEqualTo("204 NO_CONTENT");
    }























}
