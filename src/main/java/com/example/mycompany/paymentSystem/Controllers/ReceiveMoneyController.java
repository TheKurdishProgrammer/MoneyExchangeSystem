package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.Branch;
import com.example.mycompany.paymentSystem.models.Currency;
import com.example.mycompany.paymentSystem.models.DTO.TransactionDto;
import com.example.mycompany.paymentSystem.models.Receive;
import com.example.mycompany.paymentSystem.models.Transaction;
import com.example.mycompany.paymentSystem.services.BranchService;
import com.example.mycompany.paymentSystem.services.CurrencyService;
import com.example.mycompany.paymentSystem.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/receive")
public class ReceiveMoneyController {

    @Autowired
    private TransactionService transactionService;


    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private BranchService branchService;


    @RequestMapping(value = {"","/same/", "/same"})
    public String indexSame() {

        return "receive";
    }



    @RequestMapping(value = {"/different/", "/different"})
    public String indexDifferent(Model model) {

        List<Branch> branches = branchService.getBranches(); //get all
        List<Currency> currencies = currencyService.getCurrencies(); //get all

        model.addAttribute("currencies",currencies);
        model.addAttribute("branches",branches);

        return "receive_different";
    }



    @RequestMapping(value = {"/transactions/{trName}", ""})
    @ResponseBody
    public TransactionDto receiveMoney(@PathVariable(value = "trName") String searchQuery) {

        Optional<Transaction> transaction = transactionService.findById(searchQuery);

        TransactionDto transactionDto = new ModelMapper().map(transaction.orElseGet(Transaction::new),TransactionDto.class);




        //do the not found logic

        //todo retrieving transaction think again and
        // check in the html, weather go with form or without form
        return transactionDto;

    }


    @RequestMapping(value = {"/transactions/approve","/transactions/approve/"})
    @ResponseBody
    public String approveTransaction(@RequestParam(value = "trNum") String trNum){


        // make sure approve is fulfilled
        transactionService.approve(trNum);

        return "{" +
                "\"success\":\"true\"" +
                "}";
    }


    @PostMapping(value = {"/different/", "/different"})
    @ResponseBody
    public String saveReceivedTransaction(Receive receive){


        // make sure approve is fulfilled
//        transactionService.approve(trNum);

        return "{" +
                "\"success\":\"true\"" +
                "}";
    }





}
