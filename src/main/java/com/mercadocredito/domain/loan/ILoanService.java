package com.mercadocredito.domain.loan;

import com.mercadocredito.domain.loan.input.LoanInput;
import com.mercadocredito.domain.loan.output.LoanOutput;

import java.util.List;

public interface ILoanService {

    public List<Loan> getLoans(String from, String to, Integer pageNo, Integer pageSize);

    public LoanOutput postLoan(LoanInput request);

}
