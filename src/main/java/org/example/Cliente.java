package org.example;

import java.util.Date;

public class Cliente {

    protected String nome;
    protected Date data;
    protected int nif;
    protected String email;
    protected int telefone;
    protected CartaConducao carta;

    public Cliente(){
        this("", new Date(), 0, "", 0, new CartaConducao());
    }

    public Cliente(String nome, Date data, int nif, String email, int telefone, CartaConducao carta) {
        this.nome = nome;
        this.data = data;
        this.nif = nif;
        this.email = email;
        this.telefone = telefone;
        this.carta = carta;
    }

    public Cliente(Cliente c){
        this(c.getNome(), c.getData(), c.getNif(), c.getEmail(), c.getTelefone(), c.getCarta());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public CartaConducao getCarta() {
        return carta;
    }

    public void setCarta(CartaConducao carta) {
        this.carta = carta;
    }

}
