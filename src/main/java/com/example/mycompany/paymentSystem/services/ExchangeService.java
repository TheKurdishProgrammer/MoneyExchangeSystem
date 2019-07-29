package com.example.mycompany.paymentSystem.services;

import com.example.mycompany.paymentSystem.models.CustomerCurrency;
import com.example.mycompany.paymentSystem.models.Exchange;
import com.example.mycompany.paymentSystem.repositories.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;
    @Autowired
    private CustomerCurrencyService boxService;

    public Exchange save(Exchange exchange) {


        //todo handle the save , weather its succeeded or not, this and all other SAVEs also!
        return exchangeRepository.save(exchange);
    }

    public boolean exchange(Exchange exchange) {
        CustomerCurrency fromBox = boxService.findByCusIdAbdCurId(exchange.getCustomer().getId(), exchange.getFromCurrency().getId());
        CustomerCurrency toBox = boxService.findByCusIdAbdCurId(exchange.getCustomer().getId(), exchange.getToCurrency().getId());
        /*
            example, 50 USD converted to 60 000 IQD
            alg:
            1- subtract 50 from the fromBox
            2- get the convereted Amount, it should be 60 0000
            3- add it to the to Box




         */

        if (fromBox.getSum() < exchange.getAmount())
            return false;
        //handing other errors that could happen during the transaction process

        fromBox.setSum(fromBox.getSum() - exchange.getAmount());

        toBox.setSum(toBox.getSum() + exchange.getConvertedMoney());

        boxService.save(fromBox);
        boxService.save(toBox);
        //indicating that the money transfer succeeded
        return true;

    }
}
