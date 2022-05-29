package com.mercadocredito.core.user.repository;

import com.mercadocredito.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaz UserRepository
 * Objeto usado para acceder a los datos de los usuarios
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
