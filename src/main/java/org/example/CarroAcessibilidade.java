package org.example;

// Subclasse CarroAcessibilidade

public class CarroAcessibilidade extends Veiculo{

    protected boolean auto; // Tem porta automática
    protected boolean rampa; // Tem rampa para cadeira de rodas

    public CarroAcessibilidade(){
        this("", "", "", 0, 0, 0, 0.0, "", false, 0.0, false, false, false);
    }

    public CarroAcessibilidade(String marca, String modelo, String matricula, int ano, int portas, int capacidade, double velocidade, String combustivel, boolean documentos, double preco, boolean alugado, boolean auto, boolean rampa) {
        super(marca, modelo, matricula, ano, portas, capacidade, velocidade, combustivel, documentos, preco, alugado);
        this.auto = auto;
        this.rampa = rampa;
    }

    public CarroAcessibilidade(CarroAcessibilidade c) {
        this(c.getMarca(), c.getModelo(), c.getMatricula(), c.getAno(), c.getPortas(), c.getCapacidade(), c.getVelocidade(), c.getCombustivel(), c.isDocumentos(), c.getPreco(), c.isAlugado(), c.isAuto(), c.isRampa());
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public boolean isRampa() {
        return rampa;
    }

    public void setRampa(boolean rampa) {
        this.rampa = rampa;
    }

}
