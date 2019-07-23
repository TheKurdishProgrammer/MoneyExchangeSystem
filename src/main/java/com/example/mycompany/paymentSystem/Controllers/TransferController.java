package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.Currency;
import com.example.mycompany.paymentSystem.models.Customer;
import com.example.mycompany.paymentSystem.models.CustomerCurrency;
import com.example.mycompany.paymentSystem.models.Transfer;
import com.example.mycompany.paymentSystem.services.CurrencyService;
import com.example.mycompany.paymentSystem.services.CustomerCurrencyService;
import com.example.mycompany.paymentSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/transfer")
public class TransferController {


    @Autowired
    private CustomerService customerService;
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CustomerCurrencyService customerCurrencyService;


    @RequestMapping(value = {"/", ""})
    public String index(Model model) {

        List<Customer> customers = customerService.getCustomers();
        List<Currency> currencies = currencyService.getCurrencies();

        model.addAttribute("customers", customers);
        model.addAttribute("currencies", currencies);

        return "transfer";

    }


    @PostMapping(value = {"/", ""})
    @ResponseBody
    public String transferMoney(HttpServletRequest request, Transfer transfer) {

        int fromCustomer = Integer.parseInt(request.getParameter("cusID_from"));
        int toCustomer = Integer.parseInt(request.getParameter("cusID_to"));
        int currencyId = Integer.parseInt(request.getParameter("curID"));


//        Optional<Customer> fromC = customerService.findById(Integer.parseInt(fromCustomer));
//        Optional<Customer> toC = customerService.findById(Integer.parseInt(toCustomer));

        CustomerCurrency boxFrom = customerCurrencyService.findByCusIdAbdCurId(fromCustomer, currencyId);

        if (boxFrom == null)
            return "{\"success\":\"false\"}";


        // handle this situation
        if (transfer.getAmount() > boxFrom.getSum()) {
            return "{\"success\":\"false\"}";

        }

        CustomerCurrency boxTo = customerCurrencyService.findByCusIdAbdCurId(toCustomer, currencyId);


        //todo make the transfer here more robust, handling transaction issues on the database,
        customerCurrencyService.transferMoney(boxFrom, boxTo, transfer.getAmount());


        return "{\"success\":\"true\"}";


    }

}
