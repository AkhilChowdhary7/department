package com.department.department.shared;

import lombok.*;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

//Id, Name, City, State, Country, ZipCode

    private int id;

    private String name;

    private String city;

    private String state;

    private String country;

    private int zipCode;
}
