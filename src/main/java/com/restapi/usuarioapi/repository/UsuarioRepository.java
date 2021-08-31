package com.restapi.usuarioapi.repository;

import com.restapi.usuarioapi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value="from Usuario u where u.cpf = :cpf")
    Optional<Usuario> findUsuarioByCpf(Long cpf);

}
