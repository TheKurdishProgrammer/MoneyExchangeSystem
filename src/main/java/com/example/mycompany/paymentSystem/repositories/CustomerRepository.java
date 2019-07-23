package com.example.mycompany.paymentSystem.repositories;

import com.example.mycompany.paymentSystem.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
