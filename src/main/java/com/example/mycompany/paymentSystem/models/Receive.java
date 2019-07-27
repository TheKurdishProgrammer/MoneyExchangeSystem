package com.example.mycompany.paymentSystem.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Receive {
    @Id
    @GeneratedValue
    private int id;



    private String receiverName;

    private double amount;

    private String address;



    @ManyToOne
    @JoinColumn
    private Currency currency;


}
