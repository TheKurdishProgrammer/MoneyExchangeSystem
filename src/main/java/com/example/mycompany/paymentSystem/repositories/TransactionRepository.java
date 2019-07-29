package com.example.mycompany.paymentSystem.repositories;

import com.example.mycompany.paymentSystem.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {


 List<Transaction> getAllByReceiverNameContains(String name);

 List<Transaction> findAllByReceiverNameContainsAndIsApprovedFalse(String name);
}
