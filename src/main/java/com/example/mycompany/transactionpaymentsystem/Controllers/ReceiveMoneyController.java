package com.example.mycompany.transactionpaymentsystem.Controllers;


import com.example.mycompany.transactionpaymentsystem.models.DTO.TransactionDto;
import com.example.mycompany.transactionpaymentsystem.models.Transaction;
import com.example.mycompany.transactionpaymentsystem.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/receive")
public class ReceiveMoneyController {

    @Autowired
    private TransactionService transactionService;


    @RequestMapping(value = {"/", ""})
    public String index() {

        return "receive";
    }


    @RequestMapping(value = {"/transactions/{trName}", ""})
    @ResponseBody
    public TransactionDto receiveMoney(@PathVariable(value = "trName") String searchQuery, Model model) {
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


}
