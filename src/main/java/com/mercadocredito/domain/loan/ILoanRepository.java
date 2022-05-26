package com.mercadocredito.domain.loan;

import com.mercadocredito.domain.payment.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByDateBetween(String from, String to);

    List<Loan> findByUserId(Long userId);

    Page<Loan> findByDateBetween(Pageable paging,String from, String to);
}
