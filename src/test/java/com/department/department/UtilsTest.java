package com.department.department;
import com.department.department.data.DepartmentEntity;
import com.department.department.data.DepartmentRepository;
import com.department.department.exception.NotFoundException;
import com.department.department.model.DepartmentRequestModel;

import com.department.department.model.DepartmentResponseModel;
import com.department.department.shared.DepartmentDto;
import com.department.department.shared.Utils;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
//import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UtilsTest {

    @Autowired
    Utils utils;

    @Test
    public void  getDepartmentMModelListTest() throws NotFoundException{
        DepartmentDto departmentDto1=new DepartmentDto(1,"name","city","state","country",56789);
        DepartmentDto departmentDto=new DepartmentDto(2,"name1","city2","state3","country4",56589);

        List<DepartmentDto> departmentDtoList= new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        List<DepartmentResponseModel> departmentList = utils.getDepartmentResponseModelList(departmentDtoList);

        assertThat(departmentDtoList.get(0).getName()).isEqualTo(departmentList.get(0).getName());
        assertThat(departmentDtoList.get(0).getCity()).isEqualTo(departmentList.get(0).getCity());
        assertThat(departmentDtoList.get(0).getState()).isEqualTo(departmentList.get(0).getState());
        assertThat(departmentDtoList.get(0).getCountry()).isEqualTo(departmentList.get(0).getCountry());
        assertThat(departmentDtoList.get(0).getZipCode()).isEqualTo(departmentList.get(0).getZipCode());


    }

    @Test
    public void getDepartmentResponseModelListNotFoundTest(){
        List<DepartmentDto> list = null;

        assertThatThrownBy(() -> utils.getDepartmentResponseModelList(list))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("0 records found");

    }

    public void getDepartmentResponseModelListNotFoundTest2(){
        List<DepartmentDto> list = new ArrayList<>();

        assertThatThrownBy(() -> utils.getDepartmentResponseModelList(list))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("0 records found");

    }

    @Test
    public void getDepartmentResponseModelTest() throws NotFoundException{
        DepartmentDto departmentDto=new DepartmentDto(1,"name","city","state","country",56789);

        DepartmentResponseModel departmentResponseModel= utils.getDepartmentResponseModel(departmentDto);

        assertThat(departmentDto.getName()).isEqualTo(departmentResponseModel.getName());
        assertThat(departmentDto.getCity()).isEqualTo(departmentResponseModel.getCity());
        assertThat(departmentDto.getState()).isEqualTo(departmentResponseModel.getState());
        assertThat(departmentDto.getCountry()).isEqualTo(departmentResponseModel.getCountry());
        assertThat(departmentDto.getZipCode()).isEqualTo(departmentResponseModel.getZipCode());

    }

    @Test
    public void getDepartmentResponseModelListNotFoundException(){
        DepartmentDto departmentDto=null;
        assertThatThrownBy(() -> utils.getDepartmentResponseModel(departmentDto))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Department not found");

    }

    @Test
    public void getDepartmentDtoTest() throws  NotFoundException{
        DepartmentRequestModel departmentDetails= new DepartmentRequestModel("name","city","state","country",56789);

        DepartmentDto departmentDto=utils.getDepartmentDto(departmentDetails);

        assertThat(departmentDto.getName()).isEqualTo(departmentDetails.getName());
        assertThat(departmentDto.getCity()).isEqualTo(departmentDetails.getCity());
        assertThat(departmentDto.getState()).isEqualTo(departmentDetails.getState());
        assertThat(departmentDto.getCountry()).isEqualTo(departmentDetails.getCountry());
        assertThat(departmentDto.getZipCode()).isEqualTo(departmentDetails.getZipCode());

    }

    @Test
    public void getDepartmentDtoNotFoundExceptionTest(){
        DepartmentRequestModel departmentDetails=null;

        assertThatThrownBy(() -> utils.getDepartmentDto(departmentDetails))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Request Object is null");


    }

    @Test
    public void getDepartmentDtoList(){
        DepartmentEntity departmentEntity = new DepartmentEntity(1,"name","city","state","country",56789);


        DepartmentEntity departmentEntity1 = new DepartmentEntity(2,"name1","city2","state3","country4",56589);

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity,departmentEntity1);

        List<DepartmentDto> allDepartments = utils.getDepartmentDtoList(departmentEntities);

        assertThat(allDepartments.get(0).getName()).isEqualTo(departmentEntity.getName());
        assertThat(allDepartments.get(0).getCity()).isEqualTo(departmentEntity.getCity());
        assertThat(allDepartments.get(0).getState()).isEqualTo(departmentEntity.getState());
        assertThat(allDepartments.get(0).getCountry()).isEqualTo(departmentEntity.getCountry());
        assertThat(allDepartments.get(0).getZipCode()).isEqualTo(departmentEntity.getZipCode());


    }

    @Test
    public void getDepartmentDtoListExceptionTest(){

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList();

        assertThatThrownBy(() -> utils.getDepartmentDtoList(departmentEntities))
                .isInstanceOf(ValidationException.class)
                .hasMessage("List of entities are null");



    }




}
