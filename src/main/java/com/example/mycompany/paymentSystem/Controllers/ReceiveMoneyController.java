package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.Branch;
import com.example.mycompany.paymentSystem.models.Currency;
import com.example.mycompany.paymentSystem.models.DTO.TransactionDto;
import com.example.mycompany.paymentSystem.models.Receive;
import com.example.mycompany.paymentSystem.models.Transaction;
import com.example.mycompany.paymentSystem.services.BranchService;
import com.example.mycompany.paymentSystem.services.CurrencyService;
import com.example.mycompany.paymentSystem.services.ReceiveMoneyServices;
import com.example.mycompany.paymentSystem.services.TransactionService;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.example.mycompany.paymentSystem.Controllers.SendMoneyController.MY_BRANCH_ID;

@Controller
@RequestMapping("/receive")
public class ReceiveMoneyController {

    @Autowired
    private TransactionService transactionService;


    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private ReceiveMoneyServices receiveServices;


    @RequestMapping(value = {"", "/same/", "/same"})
    public String indexSame() {

        return "receive";
    }


    @RequestMapping(value = {"/different/", "/different"})
    public String indexDifferent(Model model) {

        List<Branch> branches = branchService.getBranchesExcept(MY_BRANCH_ID); //get all
        List<Currency> currencies = currencyService.getCurrencies(); //get all

        model.addAttribute("currencies", currencies);
        model.addAttribute("branches", branches);

        return "receive_different";
    }


    @RequestMapping(value = {"/transactions/{trName}", ""})
    @ResponseBody
    public List<TransactionDto> receiveMoney(@PathVariable(value = "trName") String searchQuery) {


        boolean isNumeric = StringUtils.isNumeric(searchQuery);
        List<Transaction> transactions = new ArrayList<>(1);



        if (isNumeric) {
            transactionService.findById(searchQuery).ifPresent(transactions::add);
        } else {
            transactions = transactionService.findByName(searchQuery);
        }

        for (Transaction transaction : transactions)
            if (transaction.getSenderName() == null)
                transaction.setSenderName(transaction.getSenderCustomer().getName());


        List<TransactionDto> transactionDtos = new ModelMapper().map(transactions, new TypeToken<List<TransactionDto>>() {
        }.getType());

        //do the not found logic

        //todo retrieving transaction think again and
        // check in the html, weather go with form or without form
        return transactionDtos;
    }


    @RequestMapping(value = {"/transactions/approve", "/transactions/approve/"})
    @ResponseBody
    public String approveTransaction(@RequestParam(value = "trNum") String trNum) {


        // make sure approve is fulfilled
        transactionService.approve(trNum);

        return "{" +
                "\"success\":\"true\"" +
                "}";
    }


    @PostMapping(value = {"/different/", "/different"})
    @ResponseBody
    public String saveReceivedTransaction(Receive receive, HttpServletRequest request) {


        // make sure approve is fulfilled

//        transactionService.approve(trNum);


        int curId = Integer.parseInt(request.getParameter("curId"));
        int bId = Integer.parseInt(request.getParameter("bId"));


        Branch sendingBranch = branchService.getOne(bId);
        Branch receivingBranch = branchService.getOne(MY_BRANCH_ID);
        Currency currency = currencyService.getOne(curId);
        receive.setSendingBranch(sendingBranch);
        receive.setReceivingBranch(receivingBranch);
        receive.setCurrency(currency);


        receiveServices.save(receive);

        if (receiveServices.save(receive).getId() <= 0)
            throw new IllegalArgumentException("Entity Could Not Be Saved");


        return "{" +
                "\"success\":\"true\"" +
                "}";
    }


}
