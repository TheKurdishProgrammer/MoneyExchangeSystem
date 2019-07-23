package com.example.mycompany.paymentSystem.repositories;

import com.example.mycompany.paymentSystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
    
}