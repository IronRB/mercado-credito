package com.mercadocredito.domain.payment;

import com.mercadocredito.domain.loan.ILoanRepository;
import com.mercadocredito.domain.loan.Loan;
import com.mercadocredito.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentService implements IPaymentService{

    @Autowired
    private ILoanRepository iLoanRepository;

    @Autowired
    private IPaymentRepository iPaymentRepository;

    /**
     * @param loanId codigo único que identifica el préstamo
     * @param amount valor del monto del pago
     * @return un pago creado
     */
    @Override
    public Payment postPayment(Integer loanId, float amount) {
        Loan loan = iLoanRepository.findById(loanId).orElse(null);
        if(amount<=loan.getBalance()){
            loan.setBalance(loan.getBalance()-amount);
            iLoanRepository.save(loan);
            Payment payment = new Payment();
            payment.setLoanId(loanId);
            payment.setDebt(loan.getBalance());
            payment.setAmount(amount);
            iPaymentRepository.save(payment);
            return payment;
        }else{
            throw new ResourceNotFoundException(400,"El valor del monto no es correcto");
        }

    }

}
