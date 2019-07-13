package com.example.mycompany.transactionpaymentsystem.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {


    @RequestMapping(value = {"/home"})
    public String home() {
        return "index";

    }

    @RequestMapping(value = {"/about", "/about"})
    public String about() {
        return "about";

    }


}
