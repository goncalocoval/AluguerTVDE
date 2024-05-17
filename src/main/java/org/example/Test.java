package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.*;

public class Test {

    static ArrayList<Veiculo> veiculos = new ArrayList<>();
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Aluguer> alugueis = new ArrayList<>();

    // Interfaces gráficas

    private static void Inicio(){

        JFrame frame = new JFrame("Aluguer de carros TVDE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Aluguer de carros TVDE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // define o estilo do título
        titleLabel.setBorder(new EmptyBorder(20, 0, 0, 0)); // padding top: 20px
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton button1 = new JButton("Veículos");
        JButton button2 = new JButton("Aluguer");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Veiculos();
                frame.dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alugueis();
                frame.dispose();
            }
        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);

        buttonPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    private static void Veiculos(){

        JFrame frame = new JFrame("Veículos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false); // torna o frame não redimensionável
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // centraliza o frame na tela

        // Título "Veículos"
        JLabel titleLabel = new JLabel("Veículos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Define o estilo do título
        titleLabel.setBorder(new EmptyBorder(10, 10, 0, 0)); // Espaçamento externo
        frame.add(titleLabel, BorderLayout.NORTH);

        // JList scrollable
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(Veiculo v : veiculos){
            listModel.addElement(v.getMarca() + " " + v.getModelo() + " | " + v.getMatricula());
        }

        JList<String> vehicleList = new JList<>(listModel);
        vehicleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(vehicleList);
        scrollPane.setBorder(new EmptyBorder(0, 10, 0, 0)); // Espaçamento externo
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botões à direita
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 0, 10)); // 5 linhas, 1 coluna
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

        JButton button1 = new JButton("Registar");
        JButton button2 = new JButton("Detalhes");
        JButton button3 = new JButton("Editar");
        JButton button4 = new JButton("Remover");
        JButton button5 = new JButton("Voltar");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VeiculoC();
                frame.dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(vehicleList.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(new JFrame(), "Nenhum veículo selecionado!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    VeiculoR(vehicleList.getSelectedIndex());
                }

            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio();
                frame.dispose();
            }
        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);

        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setVisible(true);

    }

    private static void Alugueis(){

        JFrame frame = new JFrame("Alugueis");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false); // torna o frame não redimensionável
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // centraliza o frame na tela

        // Título "Veículos"
        JLabel titleLabel = new JLabel("Alugueis");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Define o estilo do título
        titleLabel.setBorder(new EmptyBorder(10, 10, 0, 0)); // Espaçamento externo
        frame.add(titleLabel, BorderLayout.NORTH);

        // JList scrollable
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(Aluguer a : alugueis){
            listModel.addElement(a.toString());
        }

        JList<String> alugieisList = new JList<>(listModel);
        alugieisList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(alugieisList);
        scrollPane.setBorder(new EmptyBorder(0, 10, 0, 0)); // Espaçamento externo
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botões à direita
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

        JCheckBox verTodosCheckbox = new JCheckBox("Ver todos");
        buttonPanel.add(verTodosCheckbox);
        JButton button1 = new JButton("Novo");
        JButton button2 = new JButton("Terminar");
        JButton button3 = new JButton("Voltar");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio();
                frame.dispose();
            }
        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setVisible(true);

    }

    // Veiculos CRUD

    private static void VeiculoC() {

        JFrame frame = new JFrame("Registar veículo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centralizar o frame na tela

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

        panel.add(new JLabel("Categoria:"));
        String[] categorias = {"Económico", "XL", "Executivo", "Acessibilidade"};
        JComboBox<String> comboCategorias = new JComboBox<>(categorias);
        comboCategorias.setSelectedIndex(0);
        panel.add(comboCategorias);

        panel.add(new JLabel("Marca:"));
        JTextField txtMarca = new JTextField("");
        panel.add(txtMarca);

        panel.add(new JLabel("Modelo:"));
        JTextField txtModelo = new JTextField("");
        panel.add(txtModelo);

        panel.add(new JLabel("Matrícula:"));
        JTextField txtMatricula = new JTextField("");
        panel.add(txtMatricula);

        panel.add(new JLabel("Ano:"));
        JTextField txtAno = new JTextField("");
        panel.add(txtAno);

        panel.add(new JLabel("Portas:"));
        JTextField txtPortas = new JTextField("");
        panel.add(txtPortas);

        panel.add(new JLabel("Capacidade:"));
        JTextField txtCapacidade = new JTextField("");
        panel.add(txtCapacidade);

        panel.add(new JLabel("Velocidade máxima:"));
        JTextField txtVelocidade = new JTextField("");
        panel.add(txtVelocidade);

        panel.add(new JLabel("Combustível:"));
        JTextField txtCombustivel = new JTextField("");
        panel.add(txtCombustivel);

        panel.add(new JLabel("Preço por dia (€):"));
        JTextField txtPreco = new JTextField("");
        panel.add(txtPreco);

        JCheckBox chkDocumentos = new JCheckBox("Documentos em ordem");
        panel.add(chkDocumentos);

        panel.add(new JLabel(""));

        JCheckBox chkVOne = new JCheckBox("Bluetooth");
        JCheckBox chkVTwo = new JCheckBox("Sistema de navegação");
        panel.add(chkVOne);
        panel.add(chkVTwo);

        comboCategorias.addActionListener((ActionEvent e) -> {
            switch (comboCategorias.getSelectedIndex()) {

                case 0 -> {
                    chkVOne.setText("Bluetooth");
                    chkVTwo.setText("Sistema de navegação");
                }

                case 1 -> {
                    chkVOne.setText("Sensores de estacionamento");
                    chkVTwo.setText("Tração às 4 rodas");
                }

                case 2 -> {
                    chkVOne.setText("WiFi");
                    chkVTwo.setText("Assentos com massagem");
                }

                default -> {
                    chkVOne.setText("Porta automática");
                    chkVTwo.setText("Rampa para cadeira de rodas");
                }

            }
        });

        JButton confirmButton = new JButton("Confirmar");
        JButton cancelButton = new JButton("Cancelar");

        cancelButton.addActionListener(e -> {
            Veiculos();
            frame.dispose();
        });

        confirmButton.addActionListener(e -> {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            String matricula = txtMatricula.getText();
            String anoS = txtAno.getText();
            String portasS = txtPortas.getText();
            String capacidadeS = txtCapacidade.getText();
            String velocidadeS = txtVelocidade.getText();
            String combustivel = txtCombustivel.getText();
            String precoS = txtPreco.getText();
            boolean documentos = chkDocumentos.isSelected();
            boolean chkOne = chkVOne.isSelected();
            boolean chkTwo = chkVTwo.isSelected();

            if (marca.isEmpty() || modelo.isEmpty() || matricula.isEmpty() || anoS.isEmpty() || portasS.isEmpty() || capacidadeS.isEmpty() || velocidadeS.isEmpty() || combustivel.isEmpty() || precoS.isEmpty()) {

                JOptionPane.showMessageDialog(new JFrame(), "Não podem existir campos vazios", "Erro",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                String error = "Ano inválido";

                try {

                    int ano = Integer.parseInt(anoS);
                    if ((ano > Year.now().getValue()) || (Year.now().getValue() - ano) > 7) {
                        Integer.parseInt("Error");
                    }

                    error = "Número de portas inválido";
                    int portas = Integer.parseInt(portasS);
                    if (portas < 5 || portas > 10) {
                        Integer.parseInt("Error");
                    }

                    error = "Capacidade inválida";
                    int capacidade = Integer.parseInt(capacidadeS);
                    if (capacidade < 1 || capacidade > 9) {
                        Integer.parseInt("Error");
                    }

                    error = "Velocidade inválida";
                    double velocidade = Double.parseDouble(velocidadeS);
                    if (velocidade < 50 || velocidade > 500) {
                        Integer.parseInt("Error");
                    }

                    error = "Preço inválido";
                    double preco = Double.parseDouble(precoS);
                    if (preco < 0) {
                        Integer.parseInt("Error");
                    }

                    int categoria = comboCategorias.getSelectedIndex();

                    Veiculo c = null;

                    c = switch (categoria) {
                        case 0 -> new CarroEconomico(marca, modelo, matricula, ano, portas, capacidade, velocidade, combustivel, documentos, preco, false, chkOne, chkTwo);
                        case 1 -> new CarroXL(marca, modelo, matricula, ano, portas, capacidade, velocidade, combustivel, documentos, preco, false, chkOne, chkTwo);
                        case 2 -> new CarroExecutivo(marca, modelo, matricula, ano, portas, capacidade, velocidade, combustivel, documentos, preco, false, chkOne, chkTwo);
                        default -> new CarroAcessibilidade(marca, modelo, matricula, ano, portas, capacidade, velocidade, combustivel, documentos, preco, false, chkOne, chkTwo);
                    };

                    veiculos.add(c);

                    JOptionPane.showMessageDialog(new JFrame(), "Veículo registado com sucesso", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);

                    Veiculos();
                    frame.dispose();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(new JFrame(), error, "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // 1 linha, 2 colunas
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    private static void VeiculoR(int i){

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        JLabel myLabel = new JLabel();
        myLabel.setText(veiculos.get(i).print());
        panel.add(myLabel);

        JOptionPane.showConfirmDialog(null, panel, "Detalhes do veículo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

    }

    // Alugueis CRUD

    private static void AluguerC(){



    }

    // Clientes CRUD

    private static void ClienteC(){

    }

    public static void main(String[] args) {
        Inicio();
    }

}
