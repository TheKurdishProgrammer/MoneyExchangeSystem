package com.example.mycompany.paymentSystem.repositories;

import com.example.mycompany.paymentSystem.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {


 }
