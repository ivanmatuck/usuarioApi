package com.restapi.usuarioapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {
    private String message;
    private Long cpf;


    public MessageResponseDTO(String message, Long cpf) {
        this.message = message;
        this.cpf = cpf;
    }

    public MessageResponseDTO(String message) {
        this.message = message;
    }
}
