package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.Branch;
import com.example.mycompany.paymentSystem.models.Currency;
import com.example.mycompany.paymentSystem.models.Customer;
import com.example.mycompany.paymentSystem.models.CustomerCurrency;
import com.example.mycompany.paymentSystem.services.BranchService;
import com.example.mycompany.paymentSystem.services.CurrencyService;
import com.example.mycompany.paymentSystem.services.CustomerCurrencyService;
import com.example.mycompany.paymentSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = {"/", "/home"})

public class HomeController {


    int counter = 1;

    @Autowired
    private BranchService branchService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CustomerService customerService;


    @Autowired
    private CustomerCurrencyService customerCurrencyService;

    @RequestMapping(value = {"/", ""})
    public String home() {


        if (counter == 1) {


            ///////adding the branches
            Branch b1 = new Branch();
            b1.setName("Erbil");

            Branch b2 = new Branch();
            b2.setName("Duhok");


            ///adding the currencies
            List<Currency> currencies = new ArrayList<>();

            Currency usd = new Currency();
            usd.setCurrency("USD");
            Currency iqd = new Currency();
            iqd.setCurrency("IQD");
            Currency eur = new Currency();
            eur.setCurrency("EUR");

            currencies.add(usd);
            currencies.add(iqd);
            currencies.add(eur);


            ///adding the customers

            Customer c1 = new Customer();
            c1.setName("Mohammed");
            c1.setAddress("Iskan");
            c1.setPhoneNumber(750352730);
            Customer c2 = new Customer();
            c2.setName("Zhyar");
            c2.setAddress("Iskan");
            c2.setPhoneNumber(750352330);
            Customer c3 = new Customer();
            c3.setName("Karzan");
            c3.setAddress("Iskan");
            c3.setPhoneNumber(750357330);


            ///adding the customer boxes

            CustomerCurrency box1 = new CustomerCurrency();

            box1.setCurrency(usd);
            box1.setCustomer(c1);

            CustomerCurrency box2 = new CustomerCurrency();

            box2.setCurrency(iqd);
            box2.setCustomer(c1);




            currencyService.saveAll(currencies);


            branchService.save(b1);
            branchService.save(b2);
            customerService.save(c1);
            customerService.save(c2);
            customerService.save(c3);



            customerCurrencyService.save(box1);
            customerCurrencyService.save(box2);




            counter++;
        }

        return "dashboard";


    }


}
