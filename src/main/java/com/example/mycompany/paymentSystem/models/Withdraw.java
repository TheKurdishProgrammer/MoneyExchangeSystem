package com.example.mycompany.paymentSystem.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Withdraw {

    @Id
    @GeneratedValue
    private int id;


    private String receiver;
    private double amount;

    private Date date;
    private String phoneNumber;

    private String notes;
//    @Column(length = 3)
//    private String currency;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn
    private CustomerCurrency customerCurrency;
}
