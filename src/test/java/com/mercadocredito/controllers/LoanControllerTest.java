package com.mercadocredito.controllers;

import com.mercadocredito.core.loan.domain.Loan;
import com.mercadocredito.core.loan.domain.input.LoanInput;
import com.mercadocredito.core.loan.domain.output.LoanDetailOutput;
import com.mercadocredito.core.loan.domain.output.LoanOutput;
import com.mercadocredito.core.loan.repository.ILoanRepository;
import com.mercadocredito.core.loan.services.ILoanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoanControllerTest {

    @MockBean
    private ILoanRepository iLoanRepository;

    @Autowired
    private ILoanService iLoanService;

    @Autowired
    LoanController loanController = new LoanController(iLoanService);

    @BeforeEach
    void setUp() {
        Loan mockLoan = new Loan();
        List loanDetailOutputList = new ArrayList();
        mockLoan.setId(1);
        mockLoan.setAmount(1000);
        mockLoan.setTerm(12);
        mockLoan.setRate((float)0.15);
        mockLoan.setUserId(1);
        mockLoan.setBalance(1000);
        mockLoan.setTarget("NEW");
        mockLoan.setDate("2021-08-05 02:18Z");
        loanDetailOutputList.add(mockLoan);

        when(iLoanRepository.findAll()).thenReturn(loanDetailOutputList);
    }

    @Test
    void getLoans() {
        List<LoanDetailOutput> serviceResponse;
        serviceResponse = loanController.getLoans(null,null,0,10);
        Assertions.assertEquals(1,serviceResponse.size());
    }

    @Test
    void postLoan() {
        LoanInput request = new LoanInput(1000,12,1);
        LoanOutput serviceResponse;
        serviceResponse = loanController.postLoan(request);
        Assertions.assertEquals(87.91588592529297,serviceResponse.getInstallment());
    }
}