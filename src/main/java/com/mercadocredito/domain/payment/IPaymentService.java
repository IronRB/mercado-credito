package com.mercadocredito.domain.payment;

import com.mercadocredito.domain.payment.input.PaymentInput;
import com.mercadocredito.domain.payment.output.DebtOutput;
import com.mercadocredito.domain.payment.output.PaymentOutput;

public interface IPaymentService {

    public PaymentOutput postPayment(long loanId, PaymentInput paymentInput);

    public DebtOutput getBalance(Long loanId, String date);

}
