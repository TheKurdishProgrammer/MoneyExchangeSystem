package com.example.mycompany.paymentSystem.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Deposit {


    @Id
    @GeneratedValue
    private int id;


    private double amount;

    private Date date;

//    @Column(length = 3)
//    private String currency;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn
    private CustomerCurrency customerCurrency;

}
