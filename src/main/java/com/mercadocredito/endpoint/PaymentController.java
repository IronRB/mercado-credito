package com.mercadocredito.endpoint;

import com.mercadocredito.domain.payment.IPaymentService;
import com.mercadocredito.domain.payment.Payment;
import com.mercadocredito.domain.payment.input.PaymentInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PaymentController {

    @Autowired
    private IPaymentService iPaymentService;

    @PostMapping("/payment/{loanId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Payment postPayment(@PathVariable long loanId,@RequestBody PaymentInput paymentInput){
        return iPaymentService.postPayment(loanId,paymentInput);
    }
}
