package com.mercadocredito.endpoint;

import com.mercadocredito.domain.payment.IPaymentService;
import com.mercadocredito.domain.payment.Payment;
import com.mercadocredito.domain.payment.input.PaymentInput;
import com.mercadocredito.domain.payment.output.DebtOutput;
import com.mercadocredito.domain.payment.output.PaymentOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PaymentController {

    @Autowired
    private IPaymentService iPaymentService;

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
}
