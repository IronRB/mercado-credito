package com.mercadocredito.endpoint;

import com.mercadocredito.domain.loan.ILoanService;
import com.mercadocredito.domain.loan.Loan;
import com.mercadocredito.domain.loan.input.LoanInput;
import com.mercadocredito.domain.loan.output.LoanOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoanController {
    @Autowired
    private ILoanService iLoanService;

    @GetMapping("/loan-collection")
    public List<Loan> getLoans(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return (List<Loan>) iLoanService.getLoans(from,to,pageNo,pageSize);
    }

    @PostMapping("/register-loan")
    @ResponseStatus(code = HttpStatus.CREATED)
    public LoanOutput postLoan(@RequestBody LoanInput request){return iLoanService.postLoan(request);}

}
