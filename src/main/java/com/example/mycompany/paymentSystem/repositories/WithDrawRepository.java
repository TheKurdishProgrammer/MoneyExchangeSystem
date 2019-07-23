package com.example.mycompany.paymentSystem.repositories;

import com.example.mycompany.paymentSystem.models.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithDrawRepository extends JpaRepository<Withdraw,Integer> {




}
