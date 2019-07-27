package com.example.mycompany.paymentSystem.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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


    @OneToOne(mappedBy = "branch")
    private User user;




    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sentTransactions=" + sentTransactions +
                ", receivedTransactions=" + receivedTransactions +
                '}';
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromBranch", fetch = FetchType.LAZY)
    private List<Transaction> sentTransactions;

    public List<Transaction> getSentTransactions() {

        return sentTransactions == null ? new ArrayList<>() : sentTransactions;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toBranch", fetch = FetchType.LAZY)
    private List<Transaction> receivedTransactions;

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions == null ? new ArrayList<>() : receivedTransactions;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receivingBranch",fetch = FetchType.LAZY)
    private List<Receive> receivedMoneys;

}
