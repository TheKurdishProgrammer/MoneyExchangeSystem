package com.example.mycompany.transactionpaymentsystem.Controllers;


import com.example.mycompany.transactionpaymentsystem.models.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/send")
public class SendMoneyController {


    @RequestMapping(value = {"/", ""})
    public String index() {
        return "send";
    }



    @PostMapping(value = {"/", ""})
    public String sendMoney(Transaction transaction, BindingResult result) {



        return "send";
    }
}
