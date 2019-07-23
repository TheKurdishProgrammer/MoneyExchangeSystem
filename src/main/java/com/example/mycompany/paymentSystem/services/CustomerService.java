package com.example.mycompany.paymentSystem.services;

import com.example.mycompany.paymentSystem.models.Customer;
import com.example.mycompany.paymentSystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(int customerId) {
        return customerRepository.findById(customerId);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
