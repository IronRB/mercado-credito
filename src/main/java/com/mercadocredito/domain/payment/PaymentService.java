package com.mercadocredito.domain.payment;

import com.mercadocredito.domain.loan.ILoanRepository;
import com.mercadocredito.domain.loan.Loan;
import com.mercadocredito.domain.payment.input.PaymentInput;
import com.mercadocredito.domain.payment.output.DebtOutput;
import com.mercadocredito.domain.payment.output.PaymentOutput;
import com.mercadocredito.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public DebtOutput getBalance(long loanId, String date) {
        Payment payment = iPaymentRepository.findById(loanId).orElse(null);
        DebtOutput debtOutput = new DebtOutput(payment.getDebt());
        return debtOutput;
    }

}
