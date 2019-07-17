package com.example.mycompany.transactionpaymentsystem.repositories;

import com.example.mycompany.transactionpaymentsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}