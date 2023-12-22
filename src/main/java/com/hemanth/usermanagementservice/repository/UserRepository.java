package com.hemanth.usermanagementservice.repository;

import com.hemanth.usermanagementservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{


}
