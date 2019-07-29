package com.example.mycompany.paymentSystem.services;

import com.example.mycompany.paymentSystem.models.Transaction;
import com.example.mycompany.paymentSystem.repositories.TransactionRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    private CustomerService customerService;

    public TransactionService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Transaction save(Transaction transaction) {
        transaction.setDate(new Date());


        if (StringUtils.isNumeric(transaction.getSenderName())) {
            transaction.setIsSenderCustomer(true);
            transaction.setSenderCustomer(customerService.getOne(transaction.getSenderName()));
            transaction.setSenderName(null);
        } else
            transaction.setIsSenderCustomer(false);


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

    public List<Transaction> findByName(String searchQuery) {
        return transactionRepository.findAllByReceiverNameContainsAndIsApprovedFalse(searchQuery);
    }
}
