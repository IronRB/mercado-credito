package com.mercadocredito.controllers;

import com.mercadocredito.core.loan.services.ILoanService;
import com.mercadocredito.core.loan.domain.input.LoanInput;
import com.mercadocredito.core.loan.domain.output.LoanDetailOutput;
import com.mercadocredito.core.loan.domain.output.LoanOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase LoanController
 * Contiene los metodos que exponen los endpoints para obtener la lista de prestamos y crear un prestamo
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoanController {
    private ILoanService iLoanService;

    public LoanController(ILoanService iLoanService){
        this.iLoanService = iLoanService;
    }

    /**
     * Metodo para obtener la lista de prestamos por un rango de fechas con paginación
     * @param from Fecha inicial de consulta
     * @param to Fecha final de consulta
     * @param pageNo Número de página
     * @param pageSize Número de registros por página
     * @return la lista de prestamos
     */
    @GetMapping("/loan-collection")
    public List<LoanDetailOutput> getLoans(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return iLoanService.getLoans(from,to,pageNo,pageSize);
    }

    /**
     * Metodo para crear un prestamo
     * @param request Mensaje de entrada con el monto, plazo del prestamo en número de meses y id de usuario
     * @return el id del prestamo y cálculo del valor de la cuota
     */
    @PostMapping("/register-loan")
    @ResponseStatus(code = HttpStatus.CREATED)
    public LoanOutput postLoan(@RequestBody @Validated LoanInput request){return iLoanService.postLoan(request);}

}
