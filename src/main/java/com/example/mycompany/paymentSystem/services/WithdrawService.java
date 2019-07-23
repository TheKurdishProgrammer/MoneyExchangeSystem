package com.example.mycompany.paymentSystem.services;

import com.example.mycompany.paymentSystem.models.Withdraw;
import com.example.mycompany.paymentSystem.repositories.WithDrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WithdrawService {

    @Autowired
    private WithDrawRepository withDrawRepository;


    public Withdraw save(Withdraw withdraw) {
        withdraw.setDate(new Date());

        return withDrawRepository.save(withdraw);
    }
}
