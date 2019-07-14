package com.example.mycompany.transactionpaymentsystem.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomersController {

    @RequestMapping(value = {"/",""})
    public String getCustomers() {
        return "customers";

    }
}
