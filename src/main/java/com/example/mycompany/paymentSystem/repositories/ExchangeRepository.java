package com.example.mycompany.paymentSystem.repositories;

import com.example.mycompany.paymentSystem.models.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange,Integer> {

}
