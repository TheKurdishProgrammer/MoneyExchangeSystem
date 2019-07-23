package com.example.mycompany.paymentSystem.services;

import com.example.mycompany.paymentSystem.models.CustomerCurrency;
import com.example.mycompany.paymentSystem.repositories.CustomerCurrencyRepository;
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


    public void transferMoney(CustomerCurrency boxFrom, CustomerCurrency boxTo, double amount) {
        double fromSum = boxFrom.getSum();
        double toSum = boxTo.getSum();

        fromSum = fromSum - amount;
        //save the subtracted from the source box
        boxFrom.setSum(fromSum);

        toSum = toSum + amount;
        //save the added to the des. box
        boxTo.setSum(toSum);

        this.save(boxFrom);
        this.save(boxTo);

    }
}
