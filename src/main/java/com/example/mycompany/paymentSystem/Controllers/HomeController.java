package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.*;
import com.example.mycompany.paymentSystem.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static com.example.mycompany.paymentSystem.Controllers.SendMoneyController.MY_BRANCH_ID;


/*


        //todo
        1-receive money approve button not wokring, only when entererd through ID

 */

@Controller
@RequestMapping(value = {"","/", "/home"})

public class HomeController {

    private int counter = 1;

    @Autowired
    private BranchService branchService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ReceiveMoneyServices receiveMoneyServices;


    @Autowired
    private CustomerCurrencyService customerCurrencyService;

    @RequestMapping(value = {"/", ""})
    public String home(Model model) {


        if (counter == 1) {
            MockUpdata();
            counter++;
        }



//        long s1 = System.currentTimeMillis();
//        List<Receive> branches1 = branchService.getOne(MY_BRANCH_ID).getReceivedMoneys();
//        long f1 = System.currentTimeMillis();
//
//        System.out.println(f1-s1);
//
//
//        long s2 = System.currentTimeMillis();
//        List<Receive> branches2 = receiveMoneyServices.getBranchReceives(MY_BRANCH_ID);
//
//        long f2 = System.currentTimeMillis();
//
//        System.out.println(f2-s2);

        int  branchCount = branchService.getCount();
        int sentTransCount = branchService.getBranchSendTransactionsCount(MY_BRANCH_ID);
        int receievedTransCount = branchService.getBranchReceiveTransactionsCount(MY_BRANCH_ID);
        receievedTransCount = receievedTransCount + branchService.getReceivedMoneyCount(MY_BRANCH_ID);

        model.addAttribute("branchCount",branchCount);
        model.addAttribute("branchSendTransactionsCount",sentTransCount);
        model.addAttribute("branchReceiveTransactionsCount",receievedTransCount);


        return "dashboard";



    }

    private void MockUpdata() {

        ///////adding the branches
        Branch b1 = new Branch();
        b1.setName("Erbil");

        Branch b2 = new Branch();
        b2.setName("Duhok");


        ///adding the currencies
        List<Currency> currencies = new ArrayList<>();

        Currency usd = new Currency();
        usd.setCurrency("USD");
        Currency iqd = new Currency();
        iqd.setCurrency("IQD");
        Currency eur = new Currency();
        eur.setCurrency("EUR");

        currencies.add(usd);
        currencies.add(iqd);
        currencies.add(eur);


        ///adding the customers

        Customer c1 = new Customer();
        c1.setName("Mohammed");
        c1.setAddress("Iskan");
        c1.setPhoneNumber("07503527330");

        Customer c2 = new Customer();
        c2.setName("Zhyar");
        c2.setAddress("Iskan");
        c2.setPhoneNumber("07503527330");

        Customer c3 = new Customer();
        c3.setName("Karzan");
        c3.setAddress("Iskan");
        c3.setPhoneNumber("07503527330");


        ///adding the customer boxes

        CustomerCurrency box1 = new CustomerCurrency();

        box1.setCurrency(usd);
        box1.setCustomer(c1);

        CustomerCurrency box2 = new CustomerCurrency();

        box2.setCurrency(iqd);
        box2.setCustomer(c1);


        CustomerCurrency box3 = new CustomerCurrency();

        box3.setCurrency(usd);
        box3.setCustomer(c2);

        currencyService.saveAll(currencies);


        branchService.save(b1);
        branchService.save(b2);
        customerService.save(c1);
        customerService.save(c2);
        customerService.save(c3);



        customerCurrencyService.save(box1);
        customerCurrencyService.save(box2);
        customerCurrencyService.save(box3);

    }




}
