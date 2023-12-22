package com.hemanth.usermanagementservice.utils;

import com.hemanth.usermanagementservice.entities.UserEntity;
import com.hemanth.usermanagementservice.model.CreateUserRequest;
import com.hemanth.usermanagementservice.model.UpdateUserRequest;
import com.hemanth.usermanagementservice.model.UserDetails;

public class UserMapper {

    public static UserEntity toEntity(CreateUserRequest request) {
        if (request == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setMobile(request.getMobile());

        return entity;
    }


    public static UserDetails toUserDetails(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        UserDetails userDetails = new UserDetails();
        userDetails.setId(entity.getId());
        userDetails.setFirstName(entity.getFirstName());
        userDetails.setLastName(entity.getLastName());
        userDetails.setEmail(entity.getEmail());
        userDetails.setMobile(entity.getMobile());

        return userDetails;
    }

    public static UserEntity updateUserEntity(UserEntity entity, UpdateUserRequest request) {
        if (entity == null || request == null) {
            return null;
        }


        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setMobile(request.getMobile());
        // Any other fields to be updated can be added here

        return entity;
    }
}
