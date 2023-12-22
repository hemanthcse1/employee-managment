package com.hemanth.usermanagementservice.services;

import com.hemanth.usermanagementservice.entities.UserEntity;
import com.hemanth.usermanagementservice.exceptions.ResourceNotFoundException;
import com.hemanth.usermanagementservice.model.CreateUserRequest;
import com.hemanth.usermanagementservice.model.UpdateUserRequest;
import com.hemanth.usermanagementservice.model.UserDetails;
import com.hemanth.usermanagementservice.repository.UserRepository;
import com.hemanth.usermanagementservice.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserDetails createUser(CreateUserRequest createUserRequest){
        UserEntity savedUser = userRepository.save(UserMapper.toEntity(createUserRequest));
        return UserMapper.toUserDetails(savedUser);
    }

    public UserDetails getUserById(Long id){
        UserEntity userEntity = userRepository.findById(id).orElse(null);

        if(userEntity == null){
            throw new ResourceNotFoundException("User not found with id: " +id);
        }
        return UserMapper.toUserDetails(userEntity);
    }

    public UserDetails updateUser(UpdateUserRequest updateUserRequest){
        UserEntity userEntity = userRepository.findById(updateUserRequest.getId()).orElse(null);

        if(userEntity == null){
            throw new ResourceNotFoundException("User not found with id: " + updateUserRequest.getId());
        }

        UserEntity updatedUser = userRepository.save(UserMapper.updateUserEntity(userEntity, updateUserRequest));
        return UserMapper.toUserDetails(updatedUser);
    }

    public Map<String,String> deleteUser(Long id){
        UserEntity userEntity = userRepository.findById(id).orElse(null);

        if(userEntity == null){
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
        return Map.of("message", "User deleted successfully");
    }


    public List<UserDetails> getAllUsers(){
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(UserMapper::toUserDetails).collect(Collectors.toList());
    }
}
