package org.example;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Aluguer implements Pagar{

    protected int num; // Número do aluguer
    protected Veiculo v; // Veículo alugado
    protected Cliente c; // Cliente que alugou
    protected LocalDate inicio; // Data do início do aluguer
    protected LocalDate fim; // Data do fim do aluguer
    protected boolean termidado; // Aluguer terminado

    public Aluguer() {
        this(0, new Veiculo(), new Cliente(), LocalDate.now());
    }

    public Aluguer(int num, Veiculo v, Cliente c, LocalDate inicio) {
        this.num = num;
        this.v = v;
        this.c = c;
        this.inicio = inicio;
        this.fim = null;
        this.termidado = false;
    }

    public Aluguer(Aluguer a){
        this(a.getNum(), a.getV(), a.getC(), a.getInicio());
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Veiculo getV() {
        return v;
    }

    public void setV(Veiculo v) {
        this.v = v;
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public boolean isTermidado() {
        return termidado;
    }

    public void setTermidado(boolean termidado) {
        this.termidado = termidado;
    }

    // Override methods

    @Override
    public String toString() {
        return num + " | " + v.getMarca() + " " + v.getModelo() + " " + v.getAno() + " | " + c.getNome() + " - " + c.getNif() + " | " + inicio;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public double PrecoTotal() {

        long dias = DAYS.between(inicio, fim);
        return dias * v.getPreco();

    }

    @Override
    public String Recibo() {
        return "";
    }
}
