package com.example.mycompany.paymentSystem.repositories;

import com.example.mycompany.paymentSystem.models.Receive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReceiveMoneyRepository extends JpaRepository<Receive, Integer> {


    @Query("select count(id) from Receive where receiving_branch_id = ?1")
    int countByBranch(int branchid);

    @Query("from Receive r where  receiving_branch_id = ?1")
    List<Receive> getAllByReceivingBranchEquals(int branchId);
}
