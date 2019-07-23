package com.example.mycompany.paymentSystem.repositories;

import com.example.mycompany.paymentSystem.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency , Integer> {

}
