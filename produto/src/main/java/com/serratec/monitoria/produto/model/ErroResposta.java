package com.serratec.monitoria.produto.model;

import java.time.LocalDateTime;
import java.util.List;

public class ErroResposta {
    private LocalDateTime timestamp;
    private String status;
    private List<String> erros;

    public ErroResposta(String status, List<String> erros) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.erros = erros;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getErros() {
        return erros;
    }
}
