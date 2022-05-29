package com.mercadocredito.core.loan.domain.output;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase LoanDetailOutput
 * Modelo usado para mostrar el detalle de los prestamos
 */
@Data
@NoArgsConstructor
public class LoanDetailOutput {
    private long id;
    private float amount;
    private int term;
    private float rate;
    private long userId;
    private String target;
    private String date;
}
