package com.example.mycompany.transactionpaymentsystem.services;

import com.example.mycompany.transactionpaymentsystem.models.Transaction;
import com.example.mycompany.transactionpaymentsystem.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;


@Service
public class TransactionService     {

    @Autowired
    private TransactionRepository transactionRepository;

     public Transaction save(Transaction transaction) {
        transaction.setDate(new Date());
        return transactionRepository.save(transaction);
    }


    @Transactional(readOnly = true)
    public Optional<Transaction> findById(String searchQuery) {
         return transactionRepository.findById(Integer.valueOf(searchQuery));
    }

    public void approve(String trNum) {
         //todo id or name
         Transaction transaction = transactionRepository.getOne(Integer.valueOf(trNum));
         transaction.setApproved(true);
         transactionRepository.save(transaction);
    }
}
