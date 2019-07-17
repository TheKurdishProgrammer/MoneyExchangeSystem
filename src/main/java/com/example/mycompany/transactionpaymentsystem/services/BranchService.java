package com.example.mycompany.transactionpaymentsystem.services;

import com.example.mycompany.transactionpaymentsystem.models.Transaction;
import com.example.mycompany.transactionpaymentsystem.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BranchService implements IBranchService {


    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Transaction save(Transaction transaction) {
        return null;
    }
}
