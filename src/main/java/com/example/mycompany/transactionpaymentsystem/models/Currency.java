package com.example.mycompany.transactionpaymentsystem.models;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Builder
public class Currency {


    @Id
    @GeneratedValue
    private int id;

    @Column(length = 3)
    private String currency;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "currency")
    private List<CustomerCurrency> customerCurrencies;

}
