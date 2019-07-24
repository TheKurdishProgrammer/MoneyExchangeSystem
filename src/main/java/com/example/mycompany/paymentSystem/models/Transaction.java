package com.example.mycompany.paymentSystem.models;

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
    private String receiverPhoneNumber;
    private String notes;

    private Date date;

    private double sendingAmount;

    @Column(length = 3)
    private String sendingCurrency;

    @Column(length = 3)
    private String receivingCurrency;

    private double receivedMoney;

    private boolean isApproved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Branch fromBranch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Branch toBranch;


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", date=" + date +
                ", receivingCurrency='" + receivingCurrency + '\'' +
                ", receivedMoney=" + receivedMoney +
                '}';
    }
}
