package com.mercadocredito.core.payment.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase Payment
 * Entidad Pago
 */
@Data
@Entity
@NoArgsConstructor
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long loanId;
    private float debt;
    private float amount;

    @Column(name="date")
    private String date;
}
