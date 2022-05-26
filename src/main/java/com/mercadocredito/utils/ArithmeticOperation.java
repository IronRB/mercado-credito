package com.mercadocredito.utils;
/**
 * Clase ArithmeticOperation
 *
 * Contiene metodos para realizar operaciones aritmetricas
 *
 * @author Robert Carmona
 * @version 1.0
 */
public class ArithmeticOperation {
    /**
     * Calcula la cuota mensual del préstamo
     * @param r valor resultado de la división entre la tasa de interes
     * @param amount monto del préstamo
     * @param term número de cuotas
     * @return el valor de la cuota mensual del préstamo
     */
    public static float calculateInstallment(float r,int term,float amount) {
        float divisor = toPower((1+r),(term))-1;
        return (r+(r/divisor))*amount;
    }

    /**
     * Calcula la potencia de un número con un exponente dado
     * @param a número base
     * @param n número exponente
     * @return la potencia de un número
     */

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
