package com.mercadocredito.domain.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment,Integer> {
}
