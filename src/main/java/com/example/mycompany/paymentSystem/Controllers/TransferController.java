package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.Customer;
import com.example.mycompany.paymentSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/transfer")
public class TransferController {


    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = {"/",""})
    public String index(Model model) {

        List<Customer> customers = customerService.getCustomers();

        model.addAttribute("customers",customers);

        return "transfer";

    }




    @PostMapping(value = {"/",""})
    @ResponseBody
    public String transferMoney(HttpServletRequest request) {

        String customerId = request.getParameter("cusID");


        return "transfer";

    }

}
