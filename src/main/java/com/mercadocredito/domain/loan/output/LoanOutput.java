package com.mercadocredito.domain.loan.output;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanOutput {
    private int loanID;
    private float installment;
}
