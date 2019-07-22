package com.example.mycompany.transactionpaymentsystem.services;

import com.example.mycompany.transactionpaymentsystem.models.Branch;
import com.example.mycompany.transactionpaymentsystem.models.Transaction;
import com.example.mycompany.transactionpaymentsystem.repositories.BranchRepository;
import org.aspectj.apache.bcel.generic.BranchHandle;
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
}
