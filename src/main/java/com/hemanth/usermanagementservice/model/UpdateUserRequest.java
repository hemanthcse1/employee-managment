package com.hemanth.usermanagementservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;

}
