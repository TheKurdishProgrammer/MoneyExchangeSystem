package com.example.mycompany.transactionpaymentsystem.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String address;


    //todo is phone number should be string or long?
    private long phoneNumber;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = FetchType.LAZY)
    private List<Deposit> deposits;
}
