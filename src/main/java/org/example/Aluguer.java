package org.example;

import java.util.Date;

public class Aluguer implements Pagar{

    protected int num; // Número do aluguer
    protected Veiculo v; // Veículo alugado
    protected Cliente c; // Cliente que alugou
    protected Date inicio; // Data do início do aluguer
    protected Date fim; // Data do fim do aluguer
    protected int dias; // Dias submetidos ao aluguer
    protected boolean atraso; // Atraso no aluguer
    protected boolean termidado; // Aluguer terminado

    public Aluguer() {
        this(0, new Veiculo(), new Cliente(), new Date(), new Date(), 0, false, false);
    }

    public Aluguer(int num, Veiculo v, Cliente c, Date inicio, Date fim, int dias, boolean atraso, boolean termidado) {
        this.num = num;
        this.v = v;
        this.c = c;
        this.inicio = inicio;
        this.fim = fim;
        this.dias = dias;
        this.atraso = atraso;
        this.termidado = termidado;
    }

    public Aluguer(Aluguer a){
        this(a.getNum(), a.getV(), a.getC(), a.getInicio(), a.getFim(), a.getDias(), a.isAtraso(), a.isTermidado());
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

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public boolean isAtraso() {
        return atraso;
    }

    public void setAtraso(boolean atraso) {
        this.atraso = atraso;
    }

    public boolean isTermidado() {
        return termidado;
    }

    public void setTermidado(boolean termidado) {
        this.termidado = termidado;
    }

    @Override
    public double PrecoTotal() {
        return this.v.preco * this.dias;
    }

    @Override
    public String Recibo() {
        return "";
    }
}
