package com.example.mycompany.paymentSystem.repositories;

import com.example.mycompany.paymentSystem.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepositRepository extends JpaRepository<Deposit,Integer> {
}
