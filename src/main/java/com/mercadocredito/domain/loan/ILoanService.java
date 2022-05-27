package com.mercadocredito.domain.loan;

import com.mercadocredito.domain.loan.input.LoanInput;
import com.mercadocredito.domain.loan.output.LoanDetailOutput;
import com.mercadocredito.domain.loan.output.LoanOutput;

import java.util.List;

/**
 * Interface ILoanService
 * Interfaz que expone los metodos creados para manejar la lógica de negocio de los prestamos
 */
public interface ILoanService {

    /**
     * Obtener la lista de prestamos con paginación y/o con unas fechas limites
     * @param from fecha inicial de consulta
     * @param to fecha final de consulta
     * @param pageNo Número de página
     * @param pageSize Número de registros por página
     * @return la lista de prestamos paginada
     */
    public List<LoanDetailOutput> getLoans(String from, String to, Integer pageNo, Integer pageSize);

    /**
     *
     * @param request Mensaje de petición para crear un prestamo
     * @return el código unico del prestamo creado y el calculo de la cuota mensual
     */
    public LoanOutput postLoan(LoanInput request);

}
