package com.mercadocredito.domain.loan.output;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanDetailOutput {
    private long loanID;
    private float amount;
    private int term;
    private float rate;
    private long userId;
    private String target;
    private String date;
}
