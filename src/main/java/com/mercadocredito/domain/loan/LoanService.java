package com.mercadocredito.domain.loan;

/**
 * Clase LoanService
 *
 * Contiene la lógica de negocio de los préstamos
 *
 * @author Robert Carmona
 * @version 1.0
 */

import com.mercadocredito.domain.loan.input.LoanInput;
import com.mercadocredito.domain.loan.output.LoanOutput;
import com.mercadocredito.domain.target.ITargetRepository;
import com.mercadocredito.domain.target.Target;
import com.mercadocredito.domain.user.User;
import com.mercadocredito.domain.user.UserRepository;
import com.mercadocredito.utils.ArithmeticOperation;
import com.mercadocredito.utils.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class LoanService implements ILoanService {

    @Autowired
    private ILoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ITargetRepository targetRepository;

    /**
     * Obtiene la lista de prestamos filtrando por fecha y realiza paginación
     * @param from fecha inicial del filtro
     * @param to fecha final del filtro
     * @param pageNo número de pagina
     * @param pageSize tamaño de páginas
     * @return la lista de prestamos solicitada
     */
    @Override
    public List<Loan> getLoans(String from, String to, Integer pageNo, Integer pageSize){

        try{
            Pageable paging = PageRequest.of(pageNo-1, pageSize);

            Page<Loan> pagedResult = null;

            if(from != null && to != null){
                pagedResult = loanRepository.findByDateBetween(paging,from,to);
            }else {
                pagedResult = loanRepository.findAll(paging);
            }

            if(pagedResult.hasContent()) {
                return pagedResult.getContent();
            } else {
                return new ArrayList<Loan>();
            }
        }catch (IllegalArgumentException i){
            throw new IllegalArgumentException("Los parametros de paginación no son correctos");
        }

    }

    /**
     * Crea una solicitud de préstamo
     * @param request Payload que contiene el monto del prestamo, número de cuotas y el id del usuario
     * @return el id del préstamo creado y el calculo de la cuota
     */
    public LoanOutput postLoan(LoanInput request){

        float rate = 0;
        User user = userRepository.findById(request.getUserId()).orElse(null);
        user.setCant(user.getCant() + 1);
        user.setAmountTotal(user.getAmountTotal() + request.getAmount());
        List<Target> targets = targetRepository.findAll();
        for (Target target: targets) {
            if(user.getCant() >= target.getCantMin() && user.getCant() <= target.getCantMax())
            {
                user.setTarget(target.getTarget());
                rate = target.getRate();
            }
        }
        userRepository.save(user);
        Loan loan = new Loan();
        loan.setAmount(request.getAmount());
        loan.setTerm(request.getTerm());
        loan.setUserId(user.getId());
        loan.setBalance(request.getAmount());
        loan.setDate(Calendar.getDateTimeNowISO8601());

        loanRepository.save(loan);

        LoanOutput loanOutput = new LoanOutput((int)loan.getId(),ArithmeticOperation.calculateInstallment(rate/12,request.getTerm(),request.getAmount()));
        return loanOutput;
    }

}
