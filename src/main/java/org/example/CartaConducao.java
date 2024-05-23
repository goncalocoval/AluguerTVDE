package org.example;

import java.time.LocalDate;

public class CartaConducao {

    protected String numero;
    protected LocalDate validade;

    public CartaConducao(){
        this("", LocalDate.now());
    }

    public CartaConducao(String numero, LocalDate validade) {
        this.numero = numero;
        this.validade = validade;
    }

    public CartaConducao(CartaConducao c) {
        this(c.getNumero(), c.getValidade());
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public String print(){

        return "<B>Número da carta de condução: </B>" + numero + "<br>" +
                "<B>Validade da carta de condução: </B>"+ validade + "<br>";

    }

}
