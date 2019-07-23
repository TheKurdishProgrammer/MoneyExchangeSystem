package com.example.mycompany.paymentSystem.repositories;

import com.example.mycompany.paymentSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}