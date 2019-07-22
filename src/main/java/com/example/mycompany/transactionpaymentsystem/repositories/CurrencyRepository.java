package com.example.mycompany.transactionpaymentsystem.repositories;

import com.example.mycompany.transactionpaymentsystem.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency , Integer> {

}
