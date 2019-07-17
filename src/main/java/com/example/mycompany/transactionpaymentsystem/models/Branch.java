package com.example.mycompany.transactionpaymentsystem.models;


import lombok.Data;
import lombok.Lombok;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Branch {


    @Id
    @GeneratedValue
    private int id;
    private String name;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fromBranch")
    private List<Transaction> sentTransactions;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "toBranch")
    private List<Transaction> receivedTransactions;
}
