package com.example.mycompany.paymentSystem.services;

import com.example.mycompany.paymentSystem.models.Transaction;

public interface ITransactionService {

    public Transaction save(Transaction transaction);
}
