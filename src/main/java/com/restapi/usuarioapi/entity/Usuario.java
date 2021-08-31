package com.restapi.usuarioapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long cpf;

    @OneToMany(mappedBy = "usuario", targetEntity = ContatosUsuario.class, cascade = CascadeType.ALL)
    private List<ContatosUsuario> contatosUsuarios = new ArrayList<>();

    public Usuario(String name, Long cpf, List<ContatosUsuario> contatosUsuarios) {
        this.name = name;
        this.cpf = cpf;
        this.contatosUsuarios = contatosUsuarios;
    }
}
