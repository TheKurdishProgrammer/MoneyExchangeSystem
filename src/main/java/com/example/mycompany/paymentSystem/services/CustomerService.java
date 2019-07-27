package com.example.mycompany.paymentSystem.services;

import com.example.mycompany.paymentSystem.models.Currency;
import com.example.mycompany.paymentSystem.models.Customer;
import com.example.mycompany.paymentSystem.models.CustomerCurrency;
import com.example.mycompany.paymentSystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CurrencyService currencyService;


    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(int customerId) {
        return customerRepository.findById(customerId);
    }

    public Customer save(Customer customer) {



        List<CustomerCurrency> boxes = new ArrayList<>(5);
        for (Currency currency : currencyService.getCurrencies()) {

            CustomerCurrency box = new CustomerCurrency();
            box.setSum(0);
            //get branch currencies
            box.setCurrency(currency);
            box.setCustomer(customer);

        }


        customer.setCustomerCurrencies(boxes);

        return customerRepository.save(customer);
    }

    public Customer getOne(String senderId) {
        return customerRepository.getOne(Integer.parseInt(senderId));
    }
}
