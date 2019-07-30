package com.example.mycompany.paymentSystem.models.DTO;


import lombok.Data;

@Data
public class CustomerAccountCheckBalance {

    private String currency;
    private double amount;
    private int depositsCount;
    private int withdrawsCount;
    private int transfersCount;

}
