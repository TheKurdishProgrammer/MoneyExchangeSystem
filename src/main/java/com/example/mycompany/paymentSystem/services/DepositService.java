package com.example.mycompany.paymentSystem.services;

import com.example.mycompany.paymentSystem.models.Deposit;
import com.example.mycompany.paymentSystem.repositories.DepositRepository;
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
