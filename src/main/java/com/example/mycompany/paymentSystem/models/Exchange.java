package com.example.mycompany.paymentSystem.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Exchange {
    @Id
    @GeneratedValue
    private int id;


    @ManyToOne
    @JoinColumn
    private Customer customer;


    private double amount;

    @JoinColumn
    @ManyToOne
    private Currency fromCurrency;

    @JoinColumn
    @ManyToOne
    private Currency toCurrency;

    private double convertedMoney;

}
