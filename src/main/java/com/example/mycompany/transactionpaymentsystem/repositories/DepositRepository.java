package com.example.mycompany.transactionpaymentsystem.repositories;

import com.example.mycompany.transactionpaymentsystem.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepositRepository extends JpaRepository<Deposit,Integer> {
}
