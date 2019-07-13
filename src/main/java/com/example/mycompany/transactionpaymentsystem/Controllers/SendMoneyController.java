package com.example.mycompany.transactionpaymentsystem.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sendmoney")
public class SendMoneyController {


    @RequestMapping(value = {"/", ""})
    public String home() {
        return "send_money";
    }
}
