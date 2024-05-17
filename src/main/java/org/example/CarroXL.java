package org.example;

// Subclasse CarroXL

public class CarroXL extends Veiculo{

    protected boolean estacionamento; // Tem sensores de estacionamento
    protected boolean tracao; // Tem tração às 4 rodas

    public CarroXL(){
        this("", "", "", 0, 0, 0, 0.0, "", false, 0.0, false, false, false);
    }

    public CarroXL(String marca, String modelo, String matricula, int ano, int portas, int capacidade, double velocidade, String combustivel, boolean documentos, double preco, boolean alugado, boolean estacionamento, boolean tracao) {
        super(marca, modelo, matricula, ano, portas, capacidade, velocidade, combustivel, documentos, preco, alugado);
        this.estacionamento = estacionamento;
        this.tracao = tracao;
    }

    public CarroXL(CarroXL c) {
        this(c.getMarca(), c.getModelo(), c.getMatricula(), c.getAno(), c.getPortas(), c.getCapacidade(), c.getVelocidade(), c.getCombustivel(), c.isDocumentos(), c.getPreco(), c.isAlugado(), c.isEstacionamento(), c.isTracao());
    }

    public boolean isEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(boolean estacionamento) {
        this.estacionamento = estacionamento;
    }

    public boolean isTracao() {
        return tracao;
    }

    public void setTracao(boolean tracao) {
        this.tracao = tracao;
    }
}
