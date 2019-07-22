package com.example.mycompany.transactionpaymentsystem.Controllers;


import com.example.mycompany.transactionpaymentsystem.models.Currency;
import com.example.mycompany.transactionpaymentsystem.models.Customer;
import com.example.mycompany.transactionpaymentsystem.models.CustomerCurrency;
import com.example.mycompany.transactionpaymentsystem.models.Deposit;
import com.example.mycompany.transactionpaymentsystem.services.CurrencyService;
import com.example.mycompany.transactionpaymentsystem.services.CustomerCurrencyService;
import com.example.mycompany.transactionpaymentsystem.services.CustomerService;
import com.example.mycompany.transactionpaymentsystem.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private CurrencyService currencyService;


    @Autowired
    private CustomerCurrencyService customerCurrencyService;

    @RequestMapping(value = {"/",""})
    public String index(Model model) {

        List<Customer> customers = customerService.getCustomers();
        List<Currency> currencies = currencyService.findAll();

        model.addAttribute("customers",customers);
        model.addAttribute("currencies",currencies);

        return "deposit";

    }



    @PostMapping(value = {"/",""})
    public String deposit(HttpServletRequest request,Deposit deposit){





        // using the customerId and currency ID retrieve the customerCurrency,
        // then add it with the deposit
        int customerId = Integer.parseInt(request.getParameter("cusID"));
        int currencyId = Integer.parseInt(request.getParameter("curID"));

        Optional<Customer> customer = customerService.findById(customerId);


        CustomerCurrency box = customerCurrencyService.findByCusIdAbdCurId(customerId,currencyId);

        if (!customer.isPresent()){
            //handle not found exception
        } else {
//            deposit.set(customer.get());
            //it goes here
            deposit.setCustomerCurrency(box);

            depositService.save(deposit);
        }

        return "deposit";
    }

}
