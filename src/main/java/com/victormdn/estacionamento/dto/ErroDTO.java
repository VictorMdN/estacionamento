package com.victormdn.estacionamento.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

@Data
@NoArgsConstructor
public class ErroDTO {

    private String msg;

    private String cause;

    public ErroDTO(Exception e){
        if(e instanceof ResponseStatusException){
            this.msg = e.getMessage();
            if(e.getCause() != null) this.cause = e.getCause().getMessage();
        } else {
            this.msg = "Ocorreu um erro inesperado pelo sistema";
            this.cause = e.getMessage();
        }
    }
}
