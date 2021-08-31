package com.restapi.usuarioapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class ContatosUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", insertable=false, updatable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Long idUsuario;

    @Column
    @Email
    private String email;

    @Column
    @Size(min = 8, max = 16)
    private String telefone;

    @Column
    public Boolean isContatoPrincipal;

    public ContatosUsuario(Long idUsuario, String email, String telefone, Boolean isContatoPrincipal) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.telefone = telefone;
        this.isContatoPrincipal = isContatoPrincipal;
    }


}
