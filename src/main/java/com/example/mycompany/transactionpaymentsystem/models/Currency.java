package com.example.mycompany.transactionpaymentsystem.models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Currency {

    private int id;
    private String currency;

}
