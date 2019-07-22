package com.example.mycompany.transactionpaymentsystem.services;

import com.example.mycompany.transactionpaymentsystem.models.CustomerCurrency;
import com.example.mycompany.transactionpaymentsystem.repositories.CustomerCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCurrencyService {

    @Autowired
    private CustomerCurrencyRepository customerCurrencyRepository;

    public CustomerCurrency findByCusIdAbdCurId(int customerId,int currencyId){

        return customerCurrencyRepository.findCustomerCurrenciesBy(customerId,currencyId);
    }

    public CustomerCurrency save(CustomerCurrency box) {
        return customerCurrencyRepository.save(box);
    }



}
