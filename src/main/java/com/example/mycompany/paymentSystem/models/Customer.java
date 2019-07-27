package com.example.mycompany.paymentSystem.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Customer {


    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String address;


    //todo is phone number should be string or long?
    private String phoneNumber;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = FetchType.LAZY)
//    private List<Deposit> deposits;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch=FetchType.LAZY)
    private List<CustomerCurrency> customerCurrencies;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "senderCustomer",fetch=FetchType.LAZY)
    private List<Transaction> transactions;
}
