package com.mercadocredito.core.payment.domain.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DebtOutput
 * Modelo usado para retornar la deuda de los prestamos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebtOutput {
    private float balance;
}
