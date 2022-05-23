package com.mercadocredito.domain.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByLoanId(long loanId);
}
