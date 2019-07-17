package com.example.mycompany.transactionpaymentsystem.Controllers;


import org.springframework.beans.factory.support.SimpleSecurityContextProvider;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Permission;
import java.security.Security;

@Controller
@RequestMapping("/receive")
public class ReceiveMoneyController {

    @RequestMapping(value = {"/", ""})
    public String receiveMoney() {



        return "receive";
    }


}
