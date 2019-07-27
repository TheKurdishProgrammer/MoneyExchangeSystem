package com.example.mycompany.paymentSystem.services;

import com.example.mycompany.paymentSystem.models.Branch;
import com.example.mycompany.paymentSystem.models.Receive;
import com.example.mycompany.paymentSystem.repositories.ReceiveMoneyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiveMoneyServices {
    private ReceiveMoneyRepository receiveRepository;

    public ReceiveMoneyServices(ReceiveMoneyRepository receiveRepository) {
        this.receiveRepository = receiveRepository;
    }


    public Receive save(Receive receive){


        return receiveRepository.save(receive);
    }


    public List<Receive> getBranchReceives(int branchId){
       return receiveRepository.getAllByReceivingBranchEquals(branchId);
    }

}
