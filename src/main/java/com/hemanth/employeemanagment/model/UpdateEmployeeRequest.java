package com.hemanth.employeemanagment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

}
