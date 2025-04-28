package com.example.data_validation.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.data_validation.entties.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    public Optional<User> findByUserName(String userName);
}
