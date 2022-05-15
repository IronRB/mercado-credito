package com.mercadocredito.domain.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService implements ILoanService {

    @Autowired
    private ILoanRepository loanRepository;

    @Override
    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }
}
