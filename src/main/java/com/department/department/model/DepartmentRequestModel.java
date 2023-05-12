package com.department.department.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestModel {
    @JsonProperty("name")
    @NotBlank(message="name is mandatory")
    @Size(min=2,message="name cant be less than 2 characetrs")
    private String name;

    @JsonProperty("city")
    @NotBlank(message="city is mandatory")
    @Size(min=2,message="city cant be less than 2 characetrs")
    private String city;

    @JsonProperty("state")
    @NotBlank(message="state is mandatory")
    @Size(min=2,message="state cant be less than 2 characetrs")
    private String state;

    @JsonProperty("country")
    @NotBlank(message="country is mandatory")
    @Size(min=2,message="country cant be less than 2 characetrs")
    private String country;

    @JsonProperty("zipcode")
    @Positive
    @Min(value = 0,  message = "Zipcode cannot be empty")
    @Max(value = 99999, message = "Zipcode cannot have more than 5 digits")
    //@NotBlank(message="zipcode is mandatory")
    //@Size(min=2,message="zipcode cant be less than 2 characetrs")
    private int zipCode;
}
