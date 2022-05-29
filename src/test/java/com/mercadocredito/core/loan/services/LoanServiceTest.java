package com.mercadocredito.core.loan.services;

import com.mercadocredito.core.loan.domain.Loan;
import com.mercadocredito.core.loan.domain.output.LoanDetailOutput;
import com.mercadocredito.core.loan.repository.ILoanRepository;
import com.mercadocredito.core.target.repository.ITargetRepository;
import com.mercadocredito.core.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceTest {

    @Autowired
    private ILoanRepository loanRepositoryMock = Mockito.mock(ILoanRepository.class);

    @Autowired
    private UserRepository userRepositoryMock= Mockito.mock(UserRepository.class);

    @Autowired
    private ITargetRepository targetRepositoryMock = Mockito.mock(ITargetRepository.class);

    @Autowired
    LoanService loanService = new LoanService(loanRepositoryMock,userRepositoryMock,targetRepositoryMock);

    List loanDetailOutputList = new ArrayList();

    @BeforeEach
    void setUp() {
        Pageable paging = PageRequest.of(0, 10);
        LoanDetailOutput loanDetailOutputMock = new LoanDetailOutput();
        loanDetailOutputMock.setId(1);
        loanDetailOutputMock.setAmount(1000);
        loanDetailOutputMock.setTerm(12);
        loanDetailOutputMock.setRate((float)0.15);
        loanDetailOutputMock.setUserId(1);
        loanDetailOutputMock.setTarget("NEW");
        loanDetailOutputMock.setDate("2021-08-05 02:18Z");

        loanDetailOutputList.add(loanDetailOutputMock);

        Page<Loan> page = new PageImpl<>(loanDetailOutputList);

        Mockito.when(loanRepositoryMock.findByDateBetween(paging,"2021-08-05 02:18Z","2021-08-05 02:18Z")).thenReturn(page);
    }

    @Test
    void getLoans() {
        List<LoanDetailOutput> serviceResponse;
        serviceResponse = loanService.getLoans("2021-08-05 02:18Z","2021-08-05 02:18Z",1,10);
        Assertions.assertEquals(loanDetailOutputList,serviceResponse);
    }

    @Test
    void postLoan() {
    }
}