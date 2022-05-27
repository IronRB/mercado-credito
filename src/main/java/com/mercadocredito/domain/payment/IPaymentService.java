package com.mercadocredito.domain.payment;

import com.mercadocredito.domain.payment.input.PaymentInput;
import com.mercadocredito.domain.payment.output.DebtOutput;
import com.mercadocredito.domain.payment.output.PaymentOutput;

/**
 * Interfaz IPaymentService
 * Interfaz que expone los metodos usados para la logica de negocio de los pagos
 */
public interface IPaymentService {

    /**
     * Permite crear un pago
     * @param loanId Código único de un prestamo
     * @param paymentInput Mensaje de entrada necesario para crear los pagos
     * @return el Código único del prestamo del cual se esta realizando el pago y la deuda actual del prestamo
     */
    public PaymentOutput postPayment(long loanId, PaymentInput paymentInput);

    /**
     * Permite obtener la deuda que tiene un prestamo por su código único o por la fecha
     * @param loanId Código único de un prestamo
     * @param date Fecha a consultar
     * @return la deuda de un prestamo
     */
    public DebtOutput getBalance(Long loanId, String date);

    /**
     * Permite obtener el total de deuda hasta una fecha consultada o por target
     * @param date Fecha consultada
     * @param target target a consultar
     * @return el total de la deuda de los prestamos registrados hasta una fecha o por target
     */
    DebtOutput getTotalBalance(String date, String target);
}
