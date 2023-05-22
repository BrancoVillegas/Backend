package com.finanzas.backend.repository;

import com.finanzas.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long>{
    List<User> findByFirstName(String firstName);
    User findByEmail(String email);
}
