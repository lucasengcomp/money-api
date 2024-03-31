package com.lucasengcomp.moneyapi.exceptionhandler;

public class ErroMensagemPadrao {

    private String mensagemUsuario;
    private String mensagemDesenvolvedor;

    public ErroMensagemPadrao(String mensagemUsuario, String mensagemDesenvolvedor) {
        this.mensagemUsuario = mensagemUsuario;
        this.mensagemDesenvolvedor = mensagemDesenvolvedor;
    }

    public String getMensagemUsuario() {
        return mensagemUsuario;
    }

    public String getMensagemDesenvolvedor() {
        return mensagemDesenvolvedor;
    }
}
