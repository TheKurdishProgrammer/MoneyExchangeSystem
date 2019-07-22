package com.example.mycompany.transactionpaymentsystem.services;


import com.example.mycompany.transactionpaymentsystem.models.Currency;
import com.example.mycompany.transactionpaymentsystem.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;


    public Currency save(Currency currency){

        return currencyRepository.save(currency);
    }

    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    public List<Currency> saveAll(List<Currency> currencies) {
        return currencyRepository.saveAll(currencies);
    }
}
