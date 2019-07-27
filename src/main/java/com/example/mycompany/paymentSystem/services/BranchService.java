package com.example.mycompany.paymentSystem.services;

import com.example.mycompany.paymentSystem.models.Branch;
import com.example.mycompany.paymentSystem.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BranchService {


    @Autowired
    private BranchRepository branchRepository;

     public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }


    //used when we get the list of branches we want to send amount money to, excluding ourselves
    public List<Branch> getBranchesExcept(int exceptBranchId){
         return branchRepository.findAllByIdIsNotLike(exceptBranchId);
    }

    public Branch getOne(int branchId) {
        return branchRepository.getOne(branchId);
    }

    public int getCount() {
        return Math.toIntExact(branchRepository.count());
    }

    public int getBranchSendTransactionsCount(int myBranchId) {
         return branchRepository.getOne(myBranchId).getSentTransactions().size();
    }

    public int getBranchReceiveTransactionsCount(int myBranchId) {
        return branchRepository.getOne(myBranchId).getReceivedTransactions().size();
    }

    public List<Branch> getBranches() {

       return branchRepository.findAll();
    }
}
