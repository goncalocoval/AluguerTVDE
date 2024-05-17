package org.example;

import java.util.Date;

public class CartaConducao {

    protected int numero;
    protected Date validade;

    public CartaConducao(){
        this(0, new Date());
    }

    public CartaConducao(int numero, Date validade) {
        this.numero = numero;
        this.validade = validade;
    }

    public CartaConducao(CartaConducao c) {
        this(c.getNumero(), c.getValidade());
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

}
