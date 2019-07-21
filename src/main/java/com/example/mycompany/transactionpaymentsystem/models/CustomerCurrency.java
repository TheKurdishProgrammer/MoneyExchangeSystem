package com.example.mycompany.transactionpaymentsystem.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class CustomerCurrency {

    @Id
    @GeneratedValue
    private int id;


    @ManyToOne
    @JoinColumn
    private Customer customer;


    @ManyToOne
    @JoinColumn
    private Currency currency;


    @OneToMany(mappedBy = "customerCurrency",fetch = FetchType.LAZY)
    private List<Deposit> deposits;

}
