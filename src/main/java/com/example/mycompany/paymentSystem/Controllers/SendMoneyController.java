package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.Branch;
import com.example.mycompany.paymentSystem.models.Transaction;
import com.example.mycompany.paymentSystem.services.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/send")
public class SendMoneyController {

    public final static int MY_BRANCH_ID = 4;

    @Autowired
    private BranchService branchService;
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CustomerService customerService;


    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = {"/", ""})
    public String index(Model model) {

        List<Branch> branches = branchService.getBranchesExcept(MY_BRANCH_ID);


        model.addAttribute("branches", branches);
        model.addAttribute("currencies", currencyService.getCurrencies());
        model.addAttribute("customers", customerService.getCustomers());
        return "send";

    }

    @PostMapping(value = {"/", ""})
    @ResponseBody
    public String sendMoney(HttpServletRequest request, Transaction transaction, BindingResult result) {


        if (result.hasErrors()) {
            for (ObjectError allError : result.getAllErrors())
                System.out.println(allError.getDefaultMessage());
            return "send";
        }

        int branchId = Integer.parseInt(request.getParameter("id"));


        //retrieving both branches
        Branch toBranch = branchService.getOne(branchId);
        Branch fromBranch = branchService.getOne(MY_BRANCH_ID);


        transaction.setToBranch(toBranch);
        transaction.setFromBranch(fromBranch);

        transactionService.save(transaction);

        if (transaction.getSenderName() == null)
            transaction.setSenderName(transaction.getSenderCustomer().getName());

        String x = invoiceService.printInvoice(transaction);
        return x;
//        return "send";
    }
}
