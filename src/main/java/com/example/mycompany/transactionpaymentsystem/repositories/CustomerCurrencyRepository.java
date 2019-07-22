package com.example.mycompany.transactionpaymentsystem.repositories;


import com.example.mycompany.transactionpaymentsystem.models.CustomerCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCurrencyRepository extends JpaRepository<CustomerCurrency,Integer> {


    @Query("select cu from CustomerCurrency cu where customer_id =?1 and currency_id = ?2 ")
    CustomerCurrency findCustomerCurrenciesBy(int customerId,int currencyId);

}
