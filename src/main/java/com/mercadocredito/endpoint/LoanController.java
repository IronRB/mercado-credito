package com.mercadocredito.endpoint;

import com.mercadocredito.domain.loan.ILoanService;
import com.mercadocredito.domain.loan.Loan;
import com.mercadocredito.domain.loan.ILoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoanController {
    @Autowired
    private ILoanService iLoanService;

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/loan-collection")
    public List<Loan> getLoans(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return (List<Loan>) iLoanService.getLoans(from,to,pageNo,pageSize);
    }

}