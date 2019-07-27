package com.example.mycompany.paymentSystem.Controllers;


import com.example.mycompany.paymentSystem.models.Branch;
import com.example.mycompany.paymentSystem.services.BranchService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = {"/branches","/branches/"})
public class BranchController {


    @Autowired
    private BranchService branchService;

    @RequestMapping(value = {"","/"})
    public String index(Model model){



        var branches = branchService.getBranchesExcept(0);

        model.addAttribute("branches",branches);

        return "branches";
    }


    @PostMapping(value = {"","/"})
    public void createBranch(Branch branch, HttpServletResponse response) throws IOException {

        branchService.save(branch);
        response.sendRedirect("/branches");

//        return "branches";
    }
}
