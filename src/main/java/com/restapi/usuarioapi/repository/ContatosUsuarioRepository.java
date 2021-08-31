package com.restapi.usuarioapi.repository;

import com.restapi.usuarioapi.entity.ContatosUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatosUsuarioRepository extends JpaRepository<ContatosUsuario, Long> {

    @Query(value="from ContatosUsuario c where c.idUsuario = :idUsuario")
    List<ContatosUsuario> findContatosUsuarioByIdUsuario(Long idUsuario);


}
