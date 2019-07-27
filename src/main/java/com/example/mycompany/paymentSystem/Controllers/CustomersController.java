package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.Customer;
import com.example.mycompany.paymentSystem.services.CustomerService;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/customers")

public class CustomersController {


    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = {"/",""})
    public String getCustomers(Model model) {

        List<Customer> customers = customerService.getCustomers();

        model.addAttribute("customers",customers);

        return "customers";

    }


    @PostMapping(value = {"","/"})
    public void postCustomer(Customer customer, HttpServletResponse response) throws IOException {


        long s1 = System.currentTimeMillis();

        customerService.save(customer);
        long f1 = System.currentTimeMillis();

        System.out.println(f1-s1);

        response.sendRedirect("/customers");
    }
}
