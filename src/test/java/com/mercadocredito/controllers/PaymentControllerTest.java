package com.mercadocredito.controllers;

import com.mercadocredito.core.loan.domain.Loan;
import com.mercadocredito.core.loan.domain.output.LoanOutput;
import com.mercadocredito.core.loan.repository.ILoanRepository;
import com.mercadocredito.core.payment.domain.Payment;
import com.mercadocredito.core.payment.domain.input.PaymentInput;
import com.mercadocredito.core.payment.domain.output.DebtOutput;
import com.mercadocredito.core.payment.domain.output.PaymentOutput;
import com.mercadocredito.core.payment.repository.IPaymentRepository;
import com.mercadocredito.core.payment.services.IPaymentService;
import com.mercadocredito.core.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PaymentControllerTest {
    @MockBean
    private ILoanRepository iLoanRepository;

    @Autowired
    private IPaymentRepository iPaymentRepository= mock(IPaymentRepository.class);

    @Autowired
    private IPaymentService iPaymentService;

    @Autowired
    PaymentController paymentController = new PaymentController(iPaymentService);

    @BeforeEach
    void setUp() {
        /*Loan mockLoan = new Loan();
        mockLoan.setId(1);
        mockLoan.setAmount(1000);
        mockLoan.setTerm(12);
        mockLoan.setRate((float)0.15);
        mockLoan.setUserId(1);
        mockLoan.setBalance(1000);
        mockLoan.setTarget("NEW");
        mockLoan.setDate("2021-08-05 02:18Z");

        when(iLoanRepository.findById((long)1).orElse(null)).thenReturn(mockLoan);
        //doReturn(mockLoan).when(iLoanRepository).findById((long)1).orElse(null);*/
    }

    @Test
    void postPayment() {
        Payment mockPayment = new Payment();
        mockPayment.setId(1);
        mockPayment.setAmount(1000);
        mockPayment.setLoanId(2);
        mockPayment.setDebt((float)913.3936);
        PaymentOutput serviceResponse;
        PaymentInput paymentOutput = new PaymentInput((float)86.60638);
        serviceResponse = paymentController.postPayment(1,paymentOutput);
        Assertions.assertEquals(mockPayment.getDebt(),serviceResponse.getDebt());
    }

    @Test
    void postPaymentWithAmountEqualsBalance() {
        Payment mockPayment = new Payment();
        mockPayment.setId(2);
        mockPayment.setAmount(1000);
        mockPayment.setLoanId(2);
        mockPayment.setDebt((float)913.3936);

        PaymentOutput serviceResponse;
        PaymentInput paymentOutput = new PaymentInput((float)1000);
        serviceResponse = paymentController.postPayment(1,paymentOutput);
        Assertions.assertEquals(0,serviceResponse.getDebt());
    }

    @Test
    void getBalance() {
        DebtOutput serviceResponse;
        serviceResponse = paymentController.getBalance((long)2,"2022-05-28 12:51Z");
        Assertions.assertEquals(914.3936157226562,serviceResponse.getBalance());
    }

    @Test
    void getTotalBalance() {
    }
}