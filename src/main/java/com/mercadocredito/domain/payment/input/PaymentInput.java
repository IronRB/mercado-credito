package com.mercadocredito.domain.payment.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase PaymentInput
 * Modelo del mensaje de entrada de un pago
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInput {
    private float amount;
}
