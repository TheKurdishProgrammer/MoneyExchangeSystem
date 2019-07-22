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


    @Override
    public String toString() {
        return "CustomerCurrency{" +
                "customer=" + customer.getName() +
                ", currency=" + currency.getCurrency() +
                '}';
    }

    @ManyToOne
    @JoinColumn
    private Currency currency;


    @OneToMany(mappedBy = "customerCurrency",fetch = FetchType.LAZY)
    private List<Deposit> deposits;

}
