package com.example.mycompany.transactionpaymentsystem.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Transaction {


    @Id
    @GeneratedValue
    private int id;


    private String senderName;


    private String receiverName;
    private long receiverPhoneNumber;

    private String notes;
    private Date date;

    private double sendingAmount;

    @Column(length = 3)
    private String sendingCurrency;

    @Column(length = 3)
    private String receivingCurrency;

    private double receivedMoney;

    private boolean isApproved;

    @ManyToOne
    @JoinColumn
    private Branch fromBranch;

    @ManyToOne
    @JoinColumn
    private Branch toBranch;
}
