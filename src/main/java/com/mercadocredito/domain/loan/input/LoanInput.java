package com.mercadocredito.domain.loan.input;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanInput {
    private float amount;
    private int term;
    private long userId;
}
