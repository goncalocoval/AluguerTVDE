package org.example;

// Superclasse Veiculo

public class Veiculo {

    // Atributos da superclasse

    protected String marca; // Marca do veículo
    protected String modelo; // Modelo do veículo
    protected String matricula; // Matrícula do veículo - única
    protected int ano; // Ano do veículo - diferença com ano atual não pode ser superior a 7 anos
    protected int portas; // Número de portas do veículo - mínimo 5 portas
    protected int capacidade; // Capacidade do veículo - máximo 9 lugares
    protected double velocidade; // Velocidade máxima do veículo
    protected String combustivel; // Tipo de combustível do veículo
    protected boolean documentos; // Documentos em ordem
    protected double preco; // Preço do aluguer por dia
    protected boolean alugado; // Estado do aluguer do veículo

    // Construtor sem parâmentros

    public Veiculo(){
        this("", "", "", 0, 0, 0, 0.0, "", false, 0.0, false);
    }

    // Construtor com parâmetros

    public Veiculo(String marca, String modelo, String matricula, int ano, int portas, int capacidade, double velocidade, String combustivel, boolean documentos, double preco, boolean alugado) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.ano = ano;
        this.portas = portas;
        this.velocidade = velocidade;
        this.combustivel = combustivel;
        this.capacidade = capacidade;
        this.documentos = documentos;
        this.preco = preco;
        this.alugado = alugado;
    }

    // Construtor de cópia

    public Veiculo(Veiculo v){
        this(v.getMarca(), v.getModelo(), v.getMatricula(), v.getAno(), v.getPortas(), v.getCapacidade(), v.getVelocidade(), v.getCombustivel(), v.isDocumentos(), v.getPreco(), v.isAlugado());
    }

    // Getters & Setters

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public boolean isDocumentos() {
        return documentos;
    }

    public void setDocumentos(boolean documentos) {
        this.documentos = documentos;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }

    // Métodos 'Override'

    @Override
    public String toString() {
        return "Veiculo{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", matricula='" + matricula + '\'' +
                ", ano=" + ano +
                ", portas=" + portas +
                ", capacidade=" + capacidade +
                ", velocidade=" + velocidade +
                ", combustivel='" + combustivel + '\'' +
                ", documentos=" + documentos +
                ", preco=" + preco +
                ", alugado=" + alugado +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    // Método print()

    public String print(){
        return "<html>" +

                "<B>Marca: </B>" + marca + "<br>" +
                "<B>Modelo: </B>"+ modelo + "<br>" +
                "<B>Matrícula: </B>" + matricula + "<br>" +
                "<B>Ano: </B>"+ ano + "<br>" +
                "<B>Portas: </B>" + portas + "<br>" +
                "<B>Capacidade: </B>"+ capacidade + "<br>" +
                "<B>Velocidade máxima: </B>"+ velocidade + "<br>" +
                "<B>Combustível: </B>" + combustivel + "<br>" +
                "<B>Documentos em ordem: </B>"+ documentos + "<br>" +
                "<B>Preço por dia (€): </B>" + preco + "<br>" +
                "<B>Alugado: </B>"+ alugado + "<br>" +

                "</html>";
    }

}
