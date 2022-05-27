package com.mercadocredito.core.payment.repository;

import com.mercadocredito.core.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaz IPaymentRepository
 * Objeto de acceso de datos de los pagos
 */
@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * Permite consultar la lista de pagos por prestamos
     * @param loanId código único del prestamo que se quiere consultar
     * @return la lista de pagos que tiene un prestamo
     */
    List<Payment> findByLoanId(Long loanId);

    /**
     * Permite consultar la lista de pagos por fecha
     * @param date fecha que se desea consultar
     * @return la lista de pagos de una fecha enviada
     */
    List<Payment> findByDate(String date);

    /**
     * Permite consultar una lista de pagos que hay hasta la fecha ingresada
     * @param date fecha ingresada para consultar los pagos que hay hasta la fecha
     * @return la lista de pagos creados hasta una fecha ingresada
     */
    List<Payment> findByDateLessThanEqual(String date);
}
