package com.mercadocredito.domain.target;

import com.mercadocredito.domain.target.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITargetRepository extends JpaRepository<Target,Integer> {
}