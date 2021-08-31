package com.restapi.usuarioapi.dto;

import com.restapi.usuarioapi.entity.ContatosUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    @Size(min = 2, max = 100)
    private String name;

    private Long cpf;

    private List<ContatosUsuario> contatosUsuarios;

    public UsuarioDTO(String name, Long cpf, List<ContatosUsuario> contatosUsuarios) {
        this.name = name;
        this.cpf = cpf;
        this.contatosUsuarios = contatosUsuarios;
    }

}
