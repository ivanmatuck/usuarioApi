package com.restapi.usuarioapi.repository;

import com.restapi.usuarioapi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.cpf) LIKE LOWER(:cpf)")
    Optional<Usuario> findUsuarioByCpf(@Param("cpf") final String cpf);

    Usuario deleteUsuarioByCpf (String cpf);
}
