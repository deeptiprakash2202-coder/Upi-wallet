package com.deep.fintechbackend.repository;

import com.deep.fintechbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

