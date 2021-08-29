package com.restapi.usuarioapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.PrePersist;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;

    @NotEmpty
    @CPF
    private String cpf;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, max = 12)
    private String telefone;

    private boolean isContatoPrincipal;
}
