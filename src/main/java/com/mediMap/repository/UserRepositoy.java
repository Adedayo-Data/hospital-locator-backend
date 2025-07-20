package com.mediMap.repository;

import com.mediMap.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoy extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
