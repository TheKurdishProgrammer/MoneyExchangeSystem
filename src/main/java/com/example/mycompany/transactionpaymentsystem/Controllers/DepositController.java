package com.example.mycompany.transactionpaymentsystem.Controllers;


import com.example.mycompany.transactionpaymentsystem.models.Customer;
import com.example.mycompany.transactionpaymentsystem.models.Deposit;
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

    @RequestMapping(value = {"/",""})
    public String index(Model model) {

        List<Customer> customers = customerService.getCustomers();

        model.addAttribute("customers",customers);

        return "deposit";

    }



    @PostMapping(value = {"/",""})
    public String deposit(HttpServletRequest request,Deposit deposit){




        int customerId = Integer.parseInt(request.getParameter("id"));

        Optional<Customer> customer = customerService.findById(customerId);

        if (!customer.isPresent()){
            //handle not found exception
        } else {
            deposit.setCustomer(customer.get());

            depositService.save(deposit);
        }

        return "deposit";
    }

}
