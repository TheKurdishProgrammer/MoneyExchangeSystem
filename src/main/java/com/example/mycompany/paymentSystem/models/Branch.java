package com.example.mycompany.paymentSystem.models;


import lombok.Data;
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

    private String phoneNumber;

    private String address;

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sentTransactions=" + sentTransactions +
                ", receivedTransactions=" + receivedTransactions +
                '}';
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fromBranch",fetch = FetchType.LAZY)
    private List<Transaction> sentTransactions;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "toBranch",fetch = FetchType.LAZY)
    private List<Transaction> receivedTransactions;
}
