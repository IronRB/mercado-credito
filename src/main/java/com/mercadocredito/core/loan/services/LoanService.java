package com.mercadocredito.core.loan.services;

/**
 * Clase LoanService
 * Contiene la lógica de negocio de los préstamos
 *
 *
 *@author Robert Carmona
 * @version 1.0
 */

import com.mercadocredito.core.loan.domain.Loan;
import com.mercadocredito.core.loan.domain.input.LoanInput;
import com.mercadocredito.core.loan.domain.output.LoanDetailOutput;
import com.mercadocredito.core.loan.domain.output.LoanOutput;
import com.mercadocredito.core.loan.repository.ILoanRepository;
import com.mercadocredito.core.target.domain.Target;
import com.mercadocredito.core.target.repository.ITargetRepository;
import com.mercadocredito.core.user.domain.User;
import com.mercadocredito.core.user.repository.UserRepository;
import com.mercadocredito.exceptions.ResourceNotFoundException;
import com.mercadocredito.utils.ArithmeticOperation;
import com.mercadocredito.utils.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService implements ILoanService {

    @Autowired
    private ILoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ITargetRepository targetRepository;

    public LoanService(ILoanRepository loanRepository,UserRepository userRepository,ITargetRepository targetRepository) {
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
        this.targetRepository = targetRepository;
    }

    /**
     * Obtiene la lista de prestamos filtrando por fecha y realiza paginación
     * @param from fecha inicial del filtro
     * @param to fecha final del filtro
     * @param pageNo número de pagina
     * @param pageSize tamaño de páginas
     * @return la lista de prestamos solicitada
     */
    @Override
    public List<LoanDetailOutput> getLoans(String from, String to, Integer pageNo, Integer pageSize){

        try{
            Pageable paging = PageRequest.of(pageNo-1, pageSize);

            Page<Loan> pagedResult;

            if(from != null && to != null){
                pagedResult = loanRepository.findByDateBetween(paging,from,to);
            }else {
                pagedResult = loanRepository.findAll(paging);
            }

            if(pagedResult.hasContent()) {
                List<Loan> loans = pagedResult.getContent();
                List loanDetailOutputList = new ArrayList();
                for(Loan loan: loans){
                    LoanDetailOutput loanDetailOutput = new LoanDetailOutput();
                    loanDetailOutput.setId(loan.getId());
                    loanDetailOutput.setAmount(loan.getAmount());
                    loanDetailOutput.setTerm(loan.getTerm());
                    loanDetailOutput.setRate(loan.getRate());
                    loanDetailOutput.setUserId(loan.getUserId());
                    loanDetailOutput.setTarget(loan.getTarget());
                    loanDetailOutput.setDate(loan.getDate());
                    loanDetailOutputList.add(loanDetailOutput);
                }
                return loanDetailOutputList;
            } else {
                return new ArrayList<>();
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
        try{
            float rate = 0;
            User user = userRepository.findById(request.getUserId()).orElse(null);
            if(null != user){
                user.setCant(user.getCant() + 1);
                user.setAmountTotal(user.getAmountTotal() + request.getAmount());
                List<Target> targets = targetRepository.findAll();
                for (Target target: targets) {
                    if(user.getCant() >= target.getCantMin() && user.getCant() <= target.getCantMax()
                            || user.getAmountTotal() >= target.getAmountTotalMin() && user.getAmountTotal() <= target.getAmountTotalMax())
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
                loan.setRate(rate);
                loan.setTarget(user.getTarget());

                loanRepository.save(loan);

                return new LoanOutput((int)loan.getId(),
                        ArithmeticOperation.calculateInstallment(rate/12,request.getTerm(),request.getAmount()));
            }else {
                throw new ResourceNotFoundException(404,"El usuario no se encuentra registrado");
            }

        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(404,e.getMessage());
        }

    }

}
