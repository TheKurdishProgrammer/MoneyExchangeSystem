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

    private String receiverAddress;

    private String receiverPhoneNumber;



    @ManyToOne
    @JoinColumn
    private Currency currency;

    @ManyToOne
    @JoinColumn
    private Branch sendingBranch;


    @ManyToOne
    @JoinColumn
    private Branch receivingBranch;

}
