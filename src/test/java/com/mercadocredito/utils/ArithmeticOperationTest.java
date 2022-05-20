package com.mercadocredito.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArithmeticOperationTest {

    @Test
    void calculateInstallment() {
        float r = (float)0.05/12;
        Assertions.assertEquals(85.60637664794922,ArithmeticOperation.calculateInstallment(r,12,1000));
    }

    @Test
    void toPower() {
        Assertions.assertEquals(4,ArithmeticOperation.toPower(2,2));
    }
}