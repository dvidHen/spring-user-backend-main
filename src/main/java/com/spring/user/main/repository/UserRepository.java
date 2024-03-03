package com.spring.user.main.repository;

import com.spring.user.main.entities.UserCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad UserCustomer.
 * Proporciona métodos para acceder y manipular los datos de los usuarios en la base de datos.
 */
@Repository
public interface UserRepository extends JpaRepository<UserCustomer, Long> {
    /**
     * Busca un usuario por su dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico del usuario a buscar.
     * @return Un Optional que puede contener el usuario encontrado, si existe.
     */
    Optional<UserCustomer> findByEmail(String email);
}
