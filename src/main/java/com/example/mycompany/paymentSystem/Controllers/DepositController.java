package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.Currency;
import com.example.mycompany.paymentSystem.models.Customer;
import com.example.mycompany.paymentSystem.models.CustomerCurrency;
import com.example.mycompany.paymentSystem.models.Deposit;
import com.example.mycompany.paymentSystem.services.CurrencyService;
import com.example.mycompany.paymentSystem.services.CustomerCurrencyService;
import com.example.mycompany.paymentSystem.services.CustomerService;
import com.example.mycompany.paymentSystem.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private CurrencyService currencyService;


    @Autowired
    private CustomerCurrencyService customerCurrencyService;

    @RequestMapping(value = {"/",""})
    public String index(Model model) {

        List<Customer> customers = customerService.getCustomers();
        List<Currency> currencies = currencyService.getCurrencies();

        model.addAttribute("customers",customers);
        model.addAttribute("currencies",currencies);

        return "deposit";

    }



    @PostMapping(value = {"/",""})
    @ResponseBody
    public String deposit(HttpServletRequest request, HttpServletResponse response, Deposit deposit,Model model) throws IOException {

        // using the customerId and currency ID retrieve the customerCurrency,
        // then add it with the deposit
        int customerId = Integer.parseInt(request.getParameter("cusID"));
        int currencyId = Integer.parseInt(request.getParameter("curID"));


        //todo what is the estifada of this customer retrieving ? :/
        Optional<Customer> customer = customerService.findById(customerId);


        CustomerCurrency box = customerCurrencyService.findByCusIdAbdCurId(customerId,currencyId);

        if (box == null){
            //handle not found exception
            //todo do this with JS
            /*
                1- check if user has this currencyBox
                2- if has, then let the browser go,
                3-if no has, then just preventDefault and the alert message is good to go :)
             */
             return "{\"success\":\"false\"}";
        } else {
//            deposit.set(customer.get());
            //it goes here
            deposit.setCustomerCurrency(box);


            double sum = box.getSum();
            sum = sum + deposit.getAmount();
            box.setSum(sum);


            customerCurrencyService.save(box);

            depositService.save(deposit);
        }

//        response.sendRedirect("/deposit");
        return "{\"success\":\"true\"}";
    }

}
