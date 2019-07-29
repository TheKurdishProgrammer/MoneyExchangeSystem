package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.Currency;
import com.example.mycompany.paymentSystem.models.Customer;
import com.example.mycompany.paymentSystem.models.Exchange;
import com.example.mycompany.paymentSystem.services.CurrencyService;
import com.example.mycompany.paymentSystem.services.CustomerCurrencyService;
import com.example.mycompany.paymentSystem.services.CustomerService;
import com.example.mycompany.paymentSystem.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {


    @Autowired
    private CustomerService customerService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private CustomerCurrencyService boxService;
    @Autowired
    private ExchangeService exchangeService;


    @RequestMapping(value = {"/", ""})
    public String index(Model model) {

        List<Customer> customers = customerService.getCustomers();
        List<Currency> currencies = currencyService.getCurrencies();

        model.addAttribute("customers", customers);
        model.addAttribute("currencies", currencies);

        return "exchange";

    }


    @PostMapping(value = {"", "/"})
    public String exchange(Exchange exchange, HttpServletRequest request) {


        String fromCurId = request.getParameter("fromCurrency");
        String toCurId = request.getParameter("toCurrency");
        String customerId = request.getParameter("customerId");
        exchange.setCustomer(customerService.getOne(customerId));

        boolean isExchanged = exchangeService.exchange(exchange);

        if (isExchanged)
            //only if its exchanged, then save the exchange
            exchangeService.save(exchange);

        return "exchange";
    }


}
