package com.mercadocredito.domain.payment.output;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentOutput {
    private long id;
    private long loanId;
    private float debt;
}
