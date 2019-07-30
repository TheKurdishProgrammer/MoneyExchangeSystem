package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.Customer;
import com.example.mycompany.paymentSystem.models.DTO.CustomerAccountCheckBalance;
import com.example.mycompany.paymentSystem.services.CustomerService;
import lombok.var;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")

public class CustomersController {


    private CustomerService customerService;

    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = {"/",""})
    public String getCustomers(Model model) {

        List<Customer> customers = customerService.getCustomers();

        model.addAttribute("customers",customers);

        return "customers";

    }

    @RequestMapping(value = {"/check/{id}","/check/{id}/"})
    @ResponseBody
    public List<CustomerAccountCheckBalance> checkAccountBalance(@PathVariable String id){

        Customer customer = customerService.getOne(id);

        List<CustomerAccountCheckBalance> balances = new ArrayList<>();

        for (var box : customer.getCustomerCurrencies()){
            var checkBalance = new CustomerAccountCheckBalance();
            checkBalance.setCurrency(box.getCurrency().getCurrency());
            checkBalance.setAmount(box.getSum());
            checkBalance.setDepositsCount(box.getDeposits().size());
            checkBalance.setWithdrawsCount(box.getWithdraws().size());
            checkBalance.setTransfersCount(box.getTransfers().size());
            balances.add(checkBalance);
        }

        

//        List<TransactionDto> transactionDtos = new ModelMapper().map(transactions, new TypeToken<List<TransactionDto>>() {
//        }.getType());

        return balances;
    }

    @PostMapping(value = {"","/"})
    public void postCustomer(Customer customer, HttpServletResponse response) throws IOException {



        long s1 = System.currentTimeMillis();

        customerService.save(customer);
        long f1 = System.currentTimeMillis();

        System.out.println(f1-s1);

        response.sendRedirect("/customers");
    }
}
