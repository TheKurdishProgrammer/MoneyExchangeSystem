package com.example.mycompany.transactionpaymentsystem.Controllers;


import com.example.mycompany.transactionpaymentsystem.models.Branch;
import com.example.mycompany.transactionpaymentsystem.models.Currency;
import com.example.mycompany.transactionpaymentsystem.models.Transaction;
import com.example.mycompany.transactionpaymentsystem.services.BranchService;
import com.example.mycompany.transactionpaymentsystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/send")
public class SendMoneyController {

    public final static int MY_BRACH_ID = 1;

    @Autowired
    private BranchService branchService;
@Autowired
    private TransactionService transactionService;


    @RequestMapping(value = {"/", ""})
    public String index(Model model) {

        List<Branch> branches = branchService.getBranchesExcept(MY_BRACH_ID);

        List<Currency> currencies = new ArrayList<>();

        currencies.add(Currency.builder().id(1).currency("USD").build());
        currencies.add(Currency.builder().id(2).currency("IQD").build());
        currencies.add(Currency.builder().id(3).currency("EUR").build());


        model.addAttribute("branches", branches);
        model.addAttribute("currencies", currencies);

        return "send";

    }

    @PostMapping(value = {"/", ""})
    public String sendMoney(HttpServletRequest request, Transaction transaction, BindingResult result) {

        if (result.hasErrors()) {
            for (ObjectError allError : result.getAllErrors())
                System.out.println(allError.getDefaultMessage());
            return "send";
        }

        Enumeration names = request.getParameterNames();
        while (names.hasMoreElements())
            System.out.println(names.nextElement().toString());



        int branchId = Integer.parseInt(request.getParameter("id"));

        transactionService.save(transaction);


        //retrieving both branches
        Branch toBranch = branchService.getOne(branchId);
        Branch fromBranch = branchService.getOne(MY_BRACH_ID);

        transaction.setToBranch(toBranch);
        transaction.setFromBranch(fromBranch);

        transactionService.save(transaction);




        return "send";
    }
}
