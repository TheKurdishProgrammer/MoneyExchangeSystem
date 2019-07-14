package com.example.mycompany.transactionpaymentsystem.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/receive")
public class ReceiveMoneyController {

    @RequestMapping(value = {"/", ""})
    public String receiveMoney() {
        return "receive";
    }


}
