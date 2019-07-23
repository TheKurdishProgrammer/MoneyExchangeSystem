package com.example.mycompany.paymentSystem.repositories;

import com.example.mycompany.paymentSystem.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch,Integer> {

    public List<Branch> findAllByIdIsNotLike(int exceptId);
}
