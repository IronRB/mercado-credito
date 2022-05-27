package com.mercadocredito.domain.payment;

import com.mercadocredito.domain.loan.ILoanRepository;
import com.mercadocredito.domain.loan.Loan;
import com.mercadocredito.domain.payment.input.PaymentInput;
import com.mercadocredito.domain.payment.output.DebtOutput;
import com.mercadocredito.domain.payment.output.PaymentOutput;
import com.mercadocredito.domain.user.User;
import com.mercadocredito.domain.user.UserRepository;
import com.mercadocredito.exception.ResourceNotFoundException;
import com.mercadocredito.utils.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPaymentService{

    @Autowired
    private ILoanRepository iLoanRepository;

    @Autowired
    private IPaymentRepository iPaymentRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * @param loanId codigo único que identifica el préstamo
     * @param paymentInput mensaje de entrada que contiene el valor del monto del pago
     * @return un pago creado
     */
    @Override
    public PaymentOutput postPayment(long loanId, PaymentInput paymentInput) {
        Loan loan = iLoanRepository.findById(loanId).orElse(null);
        if (null != loan){
            if(paymentInput.getAmount()<=loan.getBalance()){
                loan.setBalance(loan.getBalance()-paymentInput.getAmount());
                iLoanRepository.save(loan);
                Payment payment = new Payment();
                payment.setLoanId(loanId);
                payment.setDebt(loan.getBalance());
                payment.setAmount(paymentInput.getAmount());
                payment.setDate(Calendar.getDateTimeNowISO8601());
                iPaymentRepository.save(payment);
                return new PaymentOutput(payment.getId(),payment.getLoanId(),payment.getDebt());
            }else{
                throw new ResourceNotFoundException(400,"El valor del monto no es correcto");
            }
        }else{
            throw new ResourceNotFoundException(404,"El prestamo no se encuentra registrado");
        }
    }

    /**
     * @param loanId codigo único del prestamo
     * @param date fecha limite de consulta de deuda
     * @return el monto de la deuda
     */
    @Override
    public DebtOutput getBalance(Long loanId, String date) {
        List<Payment> payment;
        DebtOutput debtOutput;
        if (null!=loanId || null!=date){
            if(null!=loanId){
                payment = iPaymentRepository.findByLoanId(loanId);
                if(payment.size()==0){
                    throw new ResourceNotFoundException(404, "No existen registros asociados al prestamo consultado");
                }
                debtOutput = new DebtOutput(payment.get(payment.size()-1).getDebt());
            }else {
                throw new IllegalArgumentException("El id del prestamo no es correcto");
            }
            if(null!=date){
                payment = iPaymentRepository.findByDate(date);
                if(payment.size()==0){
                    throw new ResourceNotFoundException(404, "No existen registros asociados a la fecha consultada");
                }
                debtOutput = new DebtOutput(payment.get(payment.size()-1).getDebt());
            }
        }else {
            throw new IllegalArgumentException("No se enviaron parametros para realizar la consulta");
        }
        return debtOutput;
    }

    /**
     * @param date fecha limite de consulta del total de la deuda
     * @param target parametro usado para filtrar la deuda por target
     * @return el total de deuda por fecha limite o target
     */
    @Override
    public DebtOutput getTotalBalance(String date, String target) {
        float balances = 0;
        List<Payment> payment;
        DebtOutput debtOutput;
        if(date != null || target != null){
            List<Loan> loans;
            if(date != null && target == null){
                loans = iLoanRepository.findAll();
                for(Loan loan: loans){
                    payment = iPaymentRepository.findByDateLessThanEqual(date);
                    if(payment.size() != 0){
                        balances += payment.get(payment.size()-1).getDebt();
                    }else{
                        balances += loan.getBalance();
                    }
                }
            }else {
                List<User> users = userRepository.findByTarget(target);
                if(users.size() != 0){
                    for(User user: users){
                        loans = iLoanRepository.findByUserId(user.getId());
                        for(Loan loan: loans){
                            balances += loan.getBalance();
                        }
                    }
                }else{
                    throw new ResourceNotFoundException(404,"No hay registros con el target enviado");
                }
            }
            debtOutput = new DebtOutput(balances);
        }else{
            throw new ResourceNotFoundException(400,"No se enviaron parametros para realizar la consulta");
        }

        return debtOutput;
    }

}
