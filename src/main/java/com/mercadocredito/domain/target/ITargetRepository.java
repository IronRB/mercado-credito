package com.mercadocredito.domain.target;

import com.mercadocredito.domain.target.Target;
import com.mercadocredito.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITargetRepository extends JpaRepository<Target,Long> {
    Target findByTarget(String target);
}