package com.mercadocredito.core.loan.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase Loan
 *
 * Entidad prestamo
 */
@Data
@Entity
@NoArgsConstructor
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private float amount;
    private int term;
    private long userId;
    private float balance;
    private float rate;
    private String target;

    @Column(name="date")
    private String date;
}
