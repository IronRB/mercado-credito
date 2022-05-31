package com.mercadocredito.core.loan.domain.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Clase LoanInput
 * Usada como modelo del mensaje de entrada de un prestamo
 */
@Data
@AllArgsConstructor
public class LoanInput {

    /**
     * Monto del prestamo
     */
    @Min(value = 0,message = "no debe ser menor a cero")
    @NotNull(message="es obligatorio")
    private float amount;

    /**
     * Número de cuotas
     */
    @NotNull(message = "es obligatorio")
    @Min(value = 0,message = "no debe ser menor a cero")
    private int term;

    /**
     * Código unico del usuario
     */
    @NotNull(message = "es obligatorio")
    @Min(value = 0,message = "no debe ser menor a cero")
    private long userId;
}
