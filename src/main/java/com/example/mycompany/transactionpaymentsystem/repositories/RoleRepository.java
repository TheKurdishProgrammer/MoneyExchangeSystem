package com.example.mycompany.transactionpaymentsystem.repositories;

import com.example.mycompany.transactionpaymentsystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
    
}