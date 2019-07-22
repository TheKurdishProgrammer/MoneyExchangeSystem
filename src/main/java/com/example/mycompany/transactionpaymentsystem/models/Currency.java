package com.example.mycompany.transactionpaymentsystem.models;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor

public class Currency {

    @Override
    public String toString() {
        return "Currency{" +
                "currency='" + currency + '\'' +
                '}';
    }

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 3)
    private String currency;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "currency")
    private List<CustomerCurrency> customerCurrencies;

}
