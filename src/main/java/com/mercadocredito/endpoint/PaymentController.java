package com.mercadocredito.endpoint;

import com.mercadocredito.domain.payment.IPaymentService;
import com.mercadocredito.domain.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PaymentController {

    @Autowired
    private IPaymentService iPaymentService;

    @PostMapping("/payment/{loanId}")
    public Payment postPayment(@PathVariable Integer loanId,@RequestBody float amount){
        return iPaymentService.postPayment(loanId,amount);
    }
}
