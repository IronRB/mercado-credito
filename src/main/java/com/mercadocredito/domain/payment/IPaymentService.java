package com.mercadocredito.domain.payment;

public interface IPaymentService {

    public Payment postPayment(Integer loanId,float amount);

}
