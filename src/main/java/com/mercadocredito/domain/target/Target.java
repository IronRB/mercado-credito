package com.mercadocredito.domain.target;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Clase Target
 * Entidad Target
 */
@Data
@Entity
public class Target implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String target;
    private int cantMin;
    private int cantMax;
    private float amountTotalMin;
    private float amountTotalMax;
    private float rate;
    private float max;

}
