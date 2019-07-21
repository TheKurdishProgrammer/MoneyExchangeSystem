package com.example.mycompany.transactionpaymentsystem.services;

import com.example.mycompany.transactionpaymentsystem.models.Deposit;
import com.example.mycompany.transactionpaymentsystem.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    public void save(Deposit deposit) {
        deposit.setDate(new Date());
        depositRepository.save(deposit);
    }
}
