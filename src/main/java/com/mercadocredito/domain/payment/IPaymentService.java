package com.mercadocredito.domain.payment;

import com.mercadocredito.domain.payment.input.PaymentInput;

public interface IPaymentService {

    public Payment postPayment(long loanId, PaymentInput paymentInput);

}
