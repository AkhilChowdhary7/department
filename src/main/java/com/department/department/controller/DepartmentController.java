package com.department.department.controller;

//import com.department.department.data.DepartmentEntity;
import com.department.department.exception.NotFoundException;
import com.department.department.model.DepartmentRequestModel;
import com.department.department.model.DepartmentResponseModel;
import com.department.department.service.DepartmentService;
import com.department.department.shared.DepartmentDto;
import com.department.department.shared.Utils;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    Utils utils;

    @GetMapping()
    public ResponseEntity<List<DepartmentResponseModel>> getAllDepartments() throws NotFoundException, jakarta.xml.bind.ValidationException {
        List<DepartmentDto> departments = departmentService.getAllDepartments();

        return new ResponseEntity<>(utils.getDepartmentResponseModelList(departments), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DepartmentResponseModel> getDepartmentById(@PathVariable Integer id) throws NotFoundException {
        DepartmentDto departmentDto = departmentService.getDepartmentById(id);

        return  new ResponseEntity<>(utils.getDepartmentResponseModel(departmentDto), HttpStatus.OK);

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<DepartmentResponseModel> getDepartmentByName(@PathVariable String name) throws NotFoundException {
        DepartmentDto departmentDto = departmentService.getDepartmentByName(name);


        return  new ResponseEntity<>(utils.getDepartmentResponseModel(departmentDto), HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<List<DepartmentResponseModel>> searchDepartments(
//            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "state", required = false) String state,
//            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "zipcode", required = false) String zipcode) throws NotFoundException {
        List<DepartmentDto> departments = null;
        if(city!=null && state!=null) {
            departments= departmentService.getDepartmentByStateAndCity(state,city);
        }
        else if(city!=null){
            departments= departmentService.getDepartmentByCity(city);
        }

        else if(state!=null) {
            departments = departmentService.getDepartmentByState(state);
        }

        return new ResponseEntity<>(utils.getDepartmentResponseModelList(departments), HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DepartmentResponseModel> createDepartment(@Valid @RequestBody DepartmentRequestModel  departmentDetails) throws NotFoundException {
        String name= departmentDetails.getName();
        System.out.println(departmentService.getDepartmentByName(name));
        if(departmentService.getDepartmentByName(name)!=null){
            throw new ValidationException("Department with provided name already exists");
        }

        DepartmentDto departmentDto = utils.getDepartmentDto(departmentDetails);

        DepartmentDto createdValue= departmentService.createDepartment(departmentDto);

        return new ResponseEntity<>(utils.getDepartmentResponseModel(createdValue), HttpStatus.CREATED);

    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<DepartmentResponseModel> updateDepartment(@Valid @RequestBody DepartmentRequestModel departmentDetails) throws NotFoundException {
        String name = departmentDetails.getName();
        if(departmentService.getDepartmentByName(name)==null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            throw new ValidationException("Failed to update, department not found");
        }

        DepartmentDto existingDepartment = departmentService.getDepartmentByName(name);

//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DepartmentDto departmentDto = utils.getDepartmentDto(departmentDetails);
        //modelMapper.map(departmentDetails,DepartmentDto.class);
        departmentDto.setId(existingDepartment.getId());

        DepartmentDto createdValue = departmentService.createDepartment(departmentDto);

//        DepartmentResponseModel returnValue = modelMapper.map(createdValue,DepartmentResponseModel.class);

        return new ResponseEntity<>(utils.getDepartmentResponseModel(createdValue), HttpStatus.OK);

    }

    @DeleteMapping("/{name}")
    ResponseEntity<Void> delete (@PathVariable String name){
        if ( departmentService.getDepartmentByName(name) == null){
            throw new ValidationException("Failed to delete, department not found");
        }
        boolean res = departmentService.deleteDepartmentByName(name);
        if(res){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new ValidationException("An error occurred while executing request");
    }

}
