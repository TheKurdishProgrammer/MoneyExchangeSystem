package com.example.mycompany.transactionpaymentsystem.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/send")
public class SendMoneyController {


    @RequestMapping(value = {"/", ""})
    public String sendMoney() {
        return "send";
    }
}
