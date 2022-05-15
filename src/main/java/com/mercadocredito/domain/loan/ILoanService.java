package com.mercadocredito.domain.loan;

import java.util.List;

public interface ILoanService {

    public List<Loan> getLoans(String from, String to, Integer pageNo, Integer pageSize);

}
