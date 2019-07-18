package com.example.mycompany.transactionpaymentsystem.repositories;

import com.example.mycompany.transactionpaymentsystem.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch,Integer> {

    public List<Branch> findAllByIdIsNotLike(int exceptId);
}
