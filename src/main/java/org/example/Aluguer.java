package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    // Métodos 'Override'

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

        long dias = DAYS.between(inicio, fim) + 1;
        double precototal = dias * v.getPreco();
        BigDecimal precoBD = new BigDecimal(precototal).setScale(2, RoundingMode.HALF_UP);
        precototal = precoBD.doubleValue();
        return precototal;

    }

    @Override
    public String Recibo() {

        return "<html>" +
                "<h1>Recibo</h1>" +
                "<p><b>Aluguer nº:</b> " + num + "</p><br>" +
                "<p><b>Veículo:</b> " + v.getMarca() + " " + v.getModelo() + " " + v.getAno() + "</p>" +
                "<p><b>Matrícula:</b> " + v.getMatricula() + "</p><br>" +
                "<p><b>Cliente:</b> " + c.getNome() + "</p>" +
                "<p><b>NIF:</b> " + c.getNif() + "</p>" +
                "<p><b>Telemóvel:</b> " + c.getTelemovel() + "</p><br>" +
                "<p><b>Data de início:</b> " + inicio + "</p>" +
                "<p><b>Data de fim:</b> " + fim + "</p><br>" +
                "<h2><b>Preço total:</b> " + PrecoTotal() + "€</h2>" +
                "</html>";

    }
}
