package com.mercadocredito.controllers;

import com.mercadocredito.core.loan.domain.Loan;
import com.mercadocredito.core.loan.repository.ILoanRepository;
import com.mercadocredito.core.payment.domain.Payment;
import com.mercadocredito.core.payment.domain.input.PaymentInput;
import com.mercadocredito.core.payment.domain.output.DebtOutput;
import com.mercadocredito.core.payment.domain.output.PaymentOutput;
import com.mercadocredito.core.payment.repository.IPaymentRepository;
import com.mercadocredito.core.payment.services.IPaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

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
        Loan mockLoan = new Loan();
        mockLoan.setId(1);
        mockLoan.setAmount(1000);
        mockLoan.setTerm(12);
        mockLoan.setRate((float)0.15);
        mockLoan.setUserId(1);
        mockLoan.setBalance(1000);
        mockLoan.setTarget("NEW");
        mockLoan.setDate("2021-08-05 02:18Z");

        when(iLoanRepository.findById((long)1)).thenReturn(Optional.of(mockLoan));
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
    void postPaymentValidLoanId() {
        Payment mockPayment = new Payment();
        mockPayment.setId(1);
        mockPayment.setAmount(1000);
        mockPayment.setLoanId(1);
        mockPayment.setDebt((float)913.3936);
        PaymentOutput serviceResponse;
        PaymentInput paymentOutput = new PaymentInput((float)86.60638);
        serviceResponse = paymentController.postPayment(1,paymentOutput);
        Assertions.assertEquals(mockPayment.getLoanId(),serviceResponse.getLoanId());
    }

    @Test
    void postPaymentWithAmountEqualsBalance() {
        PaymentOutput serviceResponse;
        PaymentInput paymentOutput = new PaymentInput((float)1000);
        serviceResponse = paymentController.postPayment(1,paymentOutput);
        Assertions.assertEquals(0,serviceResponse.getDebt());
    }

    @Test
    void postPaymentWithAmountMayor() {
        try{
            PaymentOutput serviceResponse;
            PaymentInput paymentOutput = new PaymentInput((float)1500);
            serviceResponse = paymentController.postPayment(1,paymentOutput);
        }catch (Exception e){
            Assertions.assertEquals("El valor del monto no es correcto",e.getMessage());
        }
    }

    @Test
    void postPaymentWithLoanNotExist() {
        try{
            PaymentOutput serviceResponse;
            PaymentInput paymentOutput = new PaymentInput((float)1000);
            serviceResponse = paymentController.postPayment(3,paymentOutput);
        }catch (Exception e){
            Assertions.assertEquals("El prestamo no se encuentra registrado",e.getMessage());
        }
    }

    @Test
    void getBalance() {
        DebtOutput serviceResponse;
        serviceResponse = paymentController.getBalance((long)2,"2022-05-28 12:51Z");
        Assertions.assertEquals(914.3936157226562,serviceResponse.getBalance());
    }

    @Test
    void getBalanceLoanNotExist() {
        try{
            paymentController.getBalance((long)5,"2022-05-28 12:51Z");
        }catch (Exception e){
            Assertions.assertEquals("No existen registros asociados al prestamo consultado",e.getMessage());
        }
    }

    @Test
    void getBalanceLoanNull() {
        try{
            paymentController.getBalance(null,"2022-05-28 12:51Z");
        }catch (Exception e){
            Assertions.assertEquals("El id del prestamo no es correcto",e.getMessage());
        }
    }

    @Test
    void getBalanceParameterInputNull() {
        try{
            paymentController.getBalance(null,null);
        }catch (Exception e){
            Assertions.assertEquals("No se enviaron parametros para realizar la consulta",e.getMessage());
        }
    }

    @Test
    void getTotalBalanceWithDate() {
        DebtOutput serviceResponse;
        serviceResponse = paymentController.getTotalBalance("2022-05-28 12:51Z",null);
        Assertions.assertEquals(0.0,serviceResponse.getBalance());
    }

    @Test
    void getTotalBalanceParameterInputNull() {
        try{
            paymentController.getTotalBalance(null,null);
        }catch(Exception e){
            Assertions.assertEquals("No se enviaron parametros para realizar la consulta",e.getMessage());
        }
    }

}