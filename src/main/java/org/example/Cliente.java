package org.example;

import java.time.LocalDate;

public class Cliente {

    protected String nome;
    protected LocalDate data;
    protected int nif;
    protected String email;
    protected int telemovel;
    protected CartaConducao carta;

    public Cliente(){
        this("", LocalDate.now(), 0, "", 0, new CartaConducao());
    }

    public Cliente(String nome, LocalDate data, int nif, String email, int telemovel, CartaConducao carta) {
        this.nome = nome;
        this.data = data;
        this.nif = nif;
        this.email = email;
        this.telemovel = telemovel;
        this.carta = carta;
    }

    public Cliente(Cliente c){
        this(c.getNome(), c.getData(), c.getNif(), c.getEmail(), c.getTelemovel(), c.getCarta());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
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

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public CartaConducao getCarta() {
        return carta;
    }

    public void setCarta(CartaConducao carta) {
        this.carta = carta;
    }

    // Métodos 'Override'


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    // Método 'print'

    // Método print()

    public String print(){
        return "<html>" +

                "<B>Nome completo: </B>" + nome + "<br>" +
                "<B>Data de nascimento: </B>"+ data + "<br>" +
                "<B>NIF: </B>" + nif + "<br>" +
                "<B>Email: </B>"+ email + "<br>" +
                "<B>Telemóvel: </B>" + telemovel + "<br>" +
                carta.print() +

                "</html>";
    }
}
