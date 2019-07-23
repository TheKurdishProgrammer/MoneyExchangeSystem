package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.Branch;
import com.example.mycompany.paymentSystem.models.Transaction;
import com.example.mycompany.paymentSystem.services.BranchService;
import com.example.mycompany.paymentSystem.services.CurrencyService;
import com.example.mycompany.paymentSystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/send")
public class SendMoneyController {

    public final static int MY_BRACH_ID = 1;

    @Autowired
    private BranchService branchService;
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CurrencyService currencyService;




    @RequestMapping(value = {"/", ""})
    public String index(Model model) {

        List<Branch> branches = branchService.getBranchesExcept(MY_BRACH_ID);



        model.addAttribute("branches", branches);
        model.addAttribute("currencies", currencyService.getCurrencies());

        return "send";

    }

    @PostMapping(value = {"/", ""})
    public String sendMoney(HttpServletRequest request, Transaction transaction, BindingResult result) {


        /*

                1- date
                2-sending and receiving currency
                3-received money
         */
        if (result.hasErrors()) {
            for (ObjectError allError : result.getAllErrors())
                System.out.println(allError.getDefaultMessage());
            return "send";
        }

//        Enumeration names = request.getParameterNames();
//        while (names.hasMoreElements())
//            System.out.println(names.nextElement().toString());


        int branchId = Integer.parseInt(request.getParameter("id"));

//        transactionService.save(transaction);


        //retrieving both branches
        Branch toBranch = branchService.getOne(branchId);
        Branch fromBranch = branchService.getOne(MY_BRACH_ID);


        transaction.setToBranch(toBranch);
        transaction.setFromBranch(fromBranch);


        transactionService.save(transaction);

        return "send";
    }
}
