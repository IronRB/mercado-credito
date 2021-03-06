package com.mercadocredito.core.loan.repository;

import com.mercadocredito.core.loan.domain.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface ILoanRepository
 * Objeto usado para acceder a los datos de los prestamos
 */
@Repository
public interface ILoanRepository extends JpaRepository<Loan,Long> {

    /**
     * Permite consultar los prestamos por usuario
     * @param userId código único del usuario
     * @return la lista de prestamos que tiene registrados el usuario consultado
     */
    List<Loan> findByUserId(Long userId);

    /**
     * Permite consultar los prestamos creados en un rango de fechas
     * @param paging objeto de paginación
     * @param from fecha inicial de consulta
     * @param to fecha final de consulta
     * @return la lista de prestamos creados en un rango de fechas
     */
    Page<Loan> findByDateBetween(Pageable paging,String from, String to);

    /**
     * Metodo que permite consultar la lista de los usuarios por target
     * @param target Target que se desea consutlar
     * @return la lista de usuarios que tiene un target especifico
     */
    List<Loan> findByTarget(String target);
}
