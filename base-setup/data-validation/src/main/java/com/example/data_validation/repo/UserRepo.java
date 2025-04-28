package com.example.data_validation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.data_validation.entties.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
