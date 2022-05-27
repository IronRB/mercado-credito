package com.mercadocredito.core.payment.domain.output;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase PaymentOutput
 * Modelo usado para el mensaje de salida cuando se crea un pago
 */
@Data
@AllArgsConstructor
public class PaymentOutput {
    private long id;
    private long loanId;
    private float debt;
}
