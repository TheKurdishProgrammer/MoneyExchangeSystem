package com.example.mycompany.transactionpaymentsystem.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transfer")
public class TransferController {


    @RequestMapping(value = {"/",""})
    public String transferMoney() {
        return "transfer";

    }

}
