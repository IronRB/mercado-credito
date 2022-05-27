package com.mercadocredito.domain.target;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz ITargetRepository
 * Objeto para acceder a los datos del target
 */
@Repository
public interface ITargetRepository extends JpaRepository<Target,Long> {

}