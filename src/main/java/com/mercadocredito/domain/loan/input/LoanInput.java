package com.mercadocredito.domain.loan.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class LoanInput {
    @Min(value = 0,message = "no debe ser menor a cero")
    @NotNull(message="es obligatorio")
    private float amount;

    @NotNull(message = "es obligatorio")
    @Min(value = 0,message = "no debe ser menor a cero")
    private int term;

    @NotNull(message = "es obligatorio")
    @Min(value = 0,message = "no debe ser menor a cero")
    private long userId;
}
