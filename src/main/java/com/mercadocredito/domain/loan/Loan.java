package com.mercadocredito.domain.loan;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private float amount;
    private int term;
    private long user_id;

    @Column(name="date")
    private Date date;
}
