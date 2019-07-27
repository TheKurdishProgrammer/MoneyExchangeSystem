package com.example.mycompany.paymentSystem.services;


import com.example.mycompany.paymentSystem.models.Currency;
import com.example.mycompany.paymentSystem.repositories.CurrencyRepository;
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

    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    public List<Currency> saveAll(List<Currency> currencies) {
        return currencyRepository.saveAll(currencies);
    }

    public Currency getOne(int curId) {
        return currencyRepository.getOne(curId);
    }
}
