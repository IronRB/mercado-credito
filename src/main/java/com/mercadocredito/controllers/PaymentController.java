package com.mercadocredito.controllers;

import com.mercadocredito.core.loan.repository.ILoanRepository;
import com.mercadocredito.core.payment.repository.IPaymentRepository;
import com.mercadocredito.core.payment.services.IPaymentService;
import com.mercadocredito.core.payment.domain.input.PaymentInput;
import com.mercadocredito.core.payment.domain.output.DebtOutput;
import com.mercadocredito.core.payment.domain.output.PaymentOutput;
import com.mercadocredito.core.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Clase PaymentController
 * Contiene los metodos que exponen los endpoints para crear un pago y consultar el total de la deuda
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class PaymentController {

    private IPaymentService iPaymentService;

    public PaymentController(IPaymentService iPaymentService) {
        this.iPaymentService = iPaymentService;
    }

    @PostMapping("/payment/{loanId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PaymentOutput postPayment(@PathVariable long loanId, @RequestBody PaymentInput paymentInput){
        return iPaymentService.postPayment(loanId,paymentInput);
    }

    @GetMapping("/payments/debts")
    @ResponseStatus(code = HttpStatus.OK)
    public DebtOutput getBalance(
            @RequestParam(required = false) Long loanId,
            @RequestParam(required = false) String date){
        return iPaymentService.getBalance(loanId,date);
    }

    @GetMapping("/payments/total-debts")
    @ResponseStatus(code = HttpStatus.OK)
    public DebtOutput getTotalBalance(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String target){
        return iPaymentService.getTotalBalance(date,target);
    }

}
