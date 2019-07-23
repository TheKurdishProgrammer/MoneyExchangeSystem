package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.Currency;
import com.example.mycompany.paymentSystem.models.Customer;
import com.example.mycompany.paymentSystem.models.CustomerCurrency;
import com.example.mycompany.paymentSystem.models.Withdraw;
import com.example.mycompany.paymentSystem.services.CurrencyService;
import com.example.mycompany.paymentSystem.services.CustomerCurrencyService;
import com.example.mycompany.paymentSystem.services.CustomerService;
import com.example.mycompany.paymentSystem.services.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/withdraw")
public class WithdrawController {

    @Autowired
    private CustomerService customerService;


    @Autowired
    private CurrencyService currencyService;


    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private CustomerCurrencyService customerCurrencyService;


    @RequestMapping(value = {"/", ""})
    public String index(Model model) {

        List<Customer> customers = customerService.getCustomers();
        List<Currency> currencies = currencyService.getCurrencies();

        model.addAttribute("customers", customers);
        model.addAttribute("currencies", currencies);

        return "withdraw";

    }


    @PostMapping(value = {"/", ""})
    @ResponseBody
    public String withdrawMoney(HttpServletRequest request, Withdraw withdraw) {

        int customerId = Integer.parseInt(request.getParameter("cusID"));
        int currencyId = Integer.parseInt(request.getParameter("curID"));

        CustomerCurrency box = customerCurrencyService.findByCusIdAbdCurId(customerId, currencyId);



        if (box == null) {
            // todo handle user has no currency
            return "{\"success\":\"false\"}";

        } else {
            /*
                    1 - if withdraw amount <= box amount , then make it zero and finish


             */

            //if withdraw amount > box sum , return false

            if (withdraw.getAmount() <= box.getSum()) {
                double sum = box.getSum();
                sum = sum - withdraw.getAmount();
                box.setSum(sum);

                withdraw.setCustomerCurrency(box);
                withdrawService.save(withdraw);

                return "{\"success\":\"true\"}";


            } else {
                // return please not enough
                return "{\"success\":\"false\"}";

            }


        }

    }
}
