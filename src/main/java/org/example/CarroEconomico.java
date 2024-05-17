package org.example;

// Subclasse CarroEconomico

public class CarroEconomico extends Veiculo{

    protected boolean bluetooth; // Tem conectividade bluetooth
    protected boolean gps; // Tem sistema de navegação

    public CarroEconomico(){
        this("", "", "", 0, 0, 0, 0.0, "", false, 0.0, false, false, false);
    }

    public CarroEconomico(String marca, String modelo, String matricula, int ano, int portas, int capacidade, double velocidade, String combustivel, boolean documentos, double preco, boolean alugado, boolean bluetooth, boolean gps) {
        super(marca, modelo, matricula, ano, portas, capacidade, velocidade, combustivel, documentos, preco, alugado);
        this.bluetooth = bluetooth;
        this.gps = gps;
    }

    public CarroEconomico(CarroEconomico c) {
        this(c.getMarca(), c.getModelo(), c.getMatricula(), c.getAno(), c.getPortas(), c.getCapacidade(), c.getVelocidade(), c.getCombustivel(), c.isDocumentos(), c.getPreco(), c.isAlugado(), c.isBluetooth(), c.isGps());
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public boolean isGps() {
        return gps;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    @Override
    public String print() {
        return super.print() +

                "<html>" +

                "<B>Bluetooth: </B>" + bluetooth + "<br>" +
                "<B>Sistema de navegação: </B>"+ gps + "<br>" +

                "</html>";
    }
}
