package com.example.mycompany.paymentSystem.repositories;

import com.example.mycompany.paymentSystem.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {


 List<Transaction> getAllByReceiverNameContains(String name);
}
