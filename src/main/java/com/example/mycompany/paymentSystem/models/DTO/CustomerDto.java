package com.example.mycompany.paymentSystem.models.DTO;


import com.example.mycompany.paymentSystem.models.Exchange;
import com.example.mycompany.paymentSystem.models.Transaction;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;


@Data
public class CustomerDto {

    private int id;

    private String name;

    private String address;



    //todo is phone number should be string or long?
    private String phoneNumber;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = FetchType.LAZY)
//    private List<Deposit> deposits;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderCustomer", fetch = FetchType.LAZY)
    private List<Transaction> transactions;





    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Exchange> exchanges;



}
