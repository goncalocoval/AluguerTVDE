package org.example;

// Subclasse CarroExecutivo

public class CarroExecutivo extends Veiculo{

    protected boolean wifi; // Tem WiFi
    protected boolean massagem; // Tem assentos massageadores

    public CarroExecutivo(){
        this("", "", "", 0, 0, 0, 0.0, "", false, 0.0, false, false, false);
    }

    public CarroExecutivo(String marca, String modelo, String matricula, int ano, int portas, int capacidade, double velocidade, String combustivel, boolean documentos, double preco, boolean alugado, boolean wifi, boolean massagem) {
        super(marca, modelo, matricula, ano, portas, capacidade, velocidade, combustivel, documentos, preco, alugado);
        this.wifi = wifi;
        this.massagem = massagem;
    }

    public CarroExecutivo(CarroExecutivo c) {
        this(c.getMarca(), c.getModelo(), c.getMatricula(), c.getAno(), c.getPortas(), c.getCapacidade(), c.getVelocidade(), c.getCombustivel(), c.isDocumentos(), c.getPreco(), c.isAlugado(), c.isWifi(), c.isMassagem());
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isMassagem() {
        return massagem;
    }

    public void setMassagem(boolean massagem) {
        this.massagem = massagem;
    }

}
