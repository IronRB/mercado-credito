package com.mercadocredito.domain.loan.output;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase LoanOutput
 * Modelo del mensaje de salida cuando se crea un prestamo
 */
@Data
@AllArgsConstructor
public class LoanOutput {
    private int loanID;
    private float installment;
}
