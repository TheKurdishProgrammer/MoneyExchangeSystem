package com.example.mycompany.transactionpaymentsystem.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/home")
public class HomeController {


    @RequestMapping(value = {"/",""})
    public String home() {
        return "dashboard";

    }




}
