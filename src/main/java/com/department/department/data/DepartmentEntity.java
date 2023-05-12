package com.department.department.data;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepartmentEntity {


//Identity Column, Name - 100 characters, City - 50 char, State, - 50 char, Country - 50 char, Zipcode - 10 char
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false,length=100, unique = true)
    private String name;

    @Column(nullable=false,length=50)
    private String city;

    @Column(nullable=false,length=50)
    private String state;

    @Column(nullable=false,length=50)
    private String country;

    @Positive
    @Column(nullable = false, length = 5)
    private int zipCode;






}
