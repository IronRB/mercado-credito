package com.mercadocredito.domain.payment;

import com.mercadocredito.domain.loan.ILoanRepository;
import com.mercadocredito.domain.loan.Loan;
import com.mercadocredito.domain.payment.input.PaymentInput;
import com.mercadocredito.domain.payment.output.DebtOutput;
import com.mercadocredito.domain.payment.output.PaymentOutput;
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

    /**
     * @param loanId codigo único que identifica el préstamo
     * @param paymentInput mensaje de entrada que contiene el valor del monto del pago
     * @return un pago creado
     */
    @Override
    public PaymentOutput postPayment(long loanId, PaymentInput paymentInput) {
        Loan loan = iLoanRepository.findById(loanId).orElse(null);
        if(paymentInput.getAmount()<=loan.getBalance()){
            loan.setBalance(loan.getBalance()-paymentInput.getAmount());
            iLoanRepository.save(loan);
            Payment payment = new Payment();
            payment.setLoanId(loanId);
            payment.setDebt(loan.getBalance());
            payment.setAmount(paymentInput.getAmount());
            payment.setDate(Calendar.getDateTimeNowISO8601());
            iPaymentRepository.save(payment);
            PaymentOutput paymentOutput = new PaymentOutput(payment.getId(),payment.getLoanId(),payment.getDebt());
            return paymentOutput;
        }else{
            throw new ResourceNotFoundException(400,"El valor del monto no es correcto");
        }

    }

    /**
     * @param loanId codigo único del prestamo
     * @param date fecha limite de consulta de deuda
     * @return el monto de la deuda
     */
    @Override
    public DebtOutput getBalance(Long loanId, String date) {
        List<Payment> payment = null;
        DebtOutput debtOutput = null;
        if (null!=loanId || null!=date){
            if(null!=loanId){
                payment = iPaymentRepository.findByLoanId(loanId);
                debtOutput = new DebtOutput(payment.get(payment.size()-1).getDebt());
            }else {
                throw new ResourceNotFoundException(400,"No se enviaron parametros para realizar la consulta");
            }
            if(null!=date){
                payment = iPaymentRepository.findByDate(date);
                debtOutput = new DebtOutput(payment.get(payment.size()-1).getDebt());
            }
        }else {
            throw new ResourceNotFoundException(400,"No se enviaron parametros para realizar la consulta");
        }
        return debtOutput;
    }

    /**
     * @param date
     * @param target
     * @return
     */
    @Override
    public DebtOutput getTotalBalance(String date, String target) {
        float balances = 0;
        List<Payment> payment = null;
        List<Loan> loans = iLoanRepository.findAll();
        for(Loan loan: loans){
            payment = iPaymentRepository.findByDateLessThanEqual(date);
            if(payment.size() != 0){
                balances += payment.get(payment.size()-1).getDebt();
            }else{
                balances += loan.getBalance();
            }
        }
        DebtOutput debtOutput = new DebtOutput(balances);
        return debtOutput;
    }

}
