package com.example.mycompany.paymentSystem.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Transfer {


    @Id
    @GeneratedValue
    private int Id;

    private double amount;



    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerCurrency customerCurrency;

}
