package com.mercadocredito.domain.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByLoanId(Long loanId);

    List<Payment> findByDate(String date);

    List<Payment> findByDateBetween(String from, String to);

    List<Payment> findByDateLessThanEqual(String date);
}
