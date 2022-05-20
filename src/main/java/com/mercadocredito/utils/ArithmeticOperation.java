package com.mercadocredito.utils;

public class ArithmeticOperation {

    public static float calculateInstallment(float r,int term,float amount) {
        float divisor = toPower((1+r),(term))-1;
        return (r+(r/divisor))*amount;
    }

    public static float toPower(float a, float n){
        float result;
        if(n == 0){
            result = 1;
        }
        else{
            // caso recursivo: a^n = a *a^n-1
            result = a * toPower(a, n - 1);
        }
        return result;
    }

}
