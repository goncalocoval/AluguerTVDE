package org.example;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    static ArrayList<Veiculo> veiculos = new ArrayList<>();
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Aluguer> alugueis = new ArrayList<>();

    static int selectedVeiculo = 0;
    static int selectedCliente = 0;

    // Interfaces gráficas

    public static void Inicio(){

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

    public static void Veiculos(){

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

                if(vehicleList.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(new JFrame(), "Nenhum veículo selecionado!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    VeiculoU(vehicleList.getSelectedIndex());
                    frame.dispose();
                }

            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(vehicleList.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(new JFrame(), "Nenhum veículo selecionado!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    VeiculoD(vehicleList.getSelectedIndex());
                    frame.dispose();
                }

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

    public static void Alugueis(){

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
        ArrayList<Integer> alugueisPositions = new ArrayList();

        for(Aluguer a : alugueis){

            if(!a.isTermidado()){
                listModel.addElement(a.toString());
                alugueisPositions.add(a.getNum());
            }

        }

        JList<String> alugueisList = new JList<>(listModel);
        alugueisList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(alugueisList);
        scrollPane.setBorder(new EmptyBorder(0, 10, 0, 0)); // Espaçamento externo
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botões à direita
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

        JCheckBox verTodosCheckbox = new JCheckBox("Ver todos");
        buttonPanel.add(verTodosCheckbox);

        verTodosCheckbox.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {

                listModel.removeAllElements();
                alugueisPositions.removeAll(alugueisPositions);

                if(verTodosCheckbox.isSelected()){

                    for(Aluguer a : alugueis){
                        listModel.addElement(a.toString());
                        alugueisPositions.add(a.getNum());
                    }

                }else{

                    for(Aluguer a : alugueis){
                        if(!a.isTermidado()){
                            listModel.addElement(a.toString());
                            alugueisPositions.add(a.getNum());
                        }
                    }

                }

                alugueisList.setModel(listModel);

            }

        });

        JButton button1 = new JButton("Novo");
        JButton button2 = new JButton("Detalhes");
        JButton button3 = new JButton("Voltar");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean flag = false;
                for(Veiculo v : veiculos){
                    if(!v.isAlugado()){
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    VeiculoS();
                    frame.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(new JFrame(), "Não existem veículos disponíveis para alugar!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(alugueisList.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(new JFrame(), "Nenhum aluguer selecionado!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    AluguerR(alugueisPositions.get(alugueisList.getSelectedIndex()));
                    frame.dispose();
                }

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

    public static void VeiculoC() {

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

            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();
            String matricula = txtMatricula.getText().toUpperCase();
            String anoS = txtAno.getText();
            String portasS = txtPortas.getText();
            String capacidadeS = txtCapacidade.getText();
            String velocidadeS = txtVelocidade.getText();
            String combustivel = txtCombustivel.getText().trim();
            String precoS = txtPreco.getText();
            boolean documentos = chkDocumentos.isSelected();
            boolean chkOne = chkVOne.isSelected();
            boolean chkTwo = chkVTwo.isSelected();

            if (marca.isEmpty() || modelo.isEmpty() || matricula.isEmpty() || anoS.isEmpty() || portasS.isEmpty() || capacidadeS.isEmpty() || velocidadeS.isEmpty() || combustivel.isEmpty() || precoS.isEmpty()) {

                JOptionPane.showMessageDialog(new JFrame(), "Não podem existir campos vazios", "Erro",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                if(VerifMatricula(matricula)){

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

    public static void VeiculoR(int i){

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        JLabel myLabel = new JLabel();
        myLabel.setText(veiculos.get(i).print());
        panel.add(myLabel);

        JOptionPane.showConfirmDialog(null, panel, "Detalhes do veículo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

    }

    public static void VeiculoU(int i){

        JFrame frame = new JFrame("Editar veículo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centralizar o frame na tela

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

        panel.add(new JLabel("Categoria:"));
        String[] categorias = {"Económico", "XL", "Executivo", "Acessibilidade"};
        JComboBox<String> comboCategorias = new JComboBox<>(categorias);

        if(veiculos.get(i) instanceof CarroEconomico){
            comboCategorias.setSelectedIndex(0);
        } else if (veiculos.get(i) instanceof CarroXL){
            comboCategorias.setSelectedIndex(1);
        } else if (veiculos.get(i) instanceof CarroExecutivo){
            comboCategorias.setSelectedIndex(2);
        } else if (veiculos.get(i) instanceof CarroAcessibilidade){
            comboCategorias.setSelectedIndex(3);
        }
        panel.add(comboCategorias);

        panel.add(new JLabel("Marca:"));
        JTextField txtMarca = new JTextField(veiculos.get(i).getMarca());
        panel.add(txtMarca);

        panel.add(new JLabel("Modelo:"));
        JTextField txtModelo = new JTextField(veiculos.get(i).getModelo());
        panel.add(txtModelo);

        panel.add(new JLabel("Matrícula:"));
        JTextField txtMatricula = new JTextField(veiculos.get(i).getMatricula());
        panel.add(txtMatricula);

        panel.add(new JLabel("Ano:"));
        JTextField txtAno = new JTextField(String.valueOf(veiculos.get(i).getAno()));
        panel.add(txtAno);

        panel.add(new JLabel("Portas:"));
        JTextField txtPortas = new JTextField(String.valueOf(veiculos.get(i).getPortas()));
        panel.add(txtPortas);

        panel.add(new JLabel("Capacidade:"));
        JTextField txtCapacidade = new JTextField(String.valueOf(veiculos.get(i).getCapacidade()));
        panel.add(txtCapacidade);

        panel.add(new JLabel("Velocidade máxima:"));
        JTextField txtVelocidade = new JTextField(String.valueOf(veiculos.get(i).getVelocidade()));
        panel.add(txtVelocidade);

        panel.add(new JLabel("Combustível:"));
        JTextField txtCombustivel = new JTextField(veiculos.get(i).getCombustivel());
        panel.add(txtCombustivel);

        panel.add(new JLabel("Preço por dia (€):"));
        JTextField txtPreco = new JTextField(String.valueOf(veiculos.get(i).getPreco()));
        panel.add(txtPreco);

        JCheckBox chkDocumentos = new JCheckBox("Documentos em ordem");
        chkDocumentos.setSelected(veiculos.get(i).isDocumentos());
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

        JButton confirmButton = new JButton("Editar");
        JButton cancelButton = new JButton("Cancelar");

        cancelButton.addActionListener(e -> {
            Veiculos();
            frame.dispose();
        });

        confirmButton.addActionListener(e -> {

            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();
            String matricula = txtMatricula.getText().toUpperCase();
            String anoS = txtAno.getText();
            String portasS = txtPortas.getText();
            String capacidadeS = txtCapacidade.getText();
            String velocidadeS = txtVelocidade.getText();
            String combustivel = txtCombustivel.getText().trim();
            String precoS = txtPreco.getText();
            boolean documentos = chkDocumentos.isSelected();
            boolean chkOne = chkVOne.isSelected();
            boolean chkTwo = chkVTwo.isSelected();

            if (marca.isEmpty() || modelo.isEmpty() || matricula.isEmpty() || anoS.isEmpty() || portasS.isEmpty() || capacidadeS.isEmpty() || velocidadeS.isEmpty() || combustivel.isEmpty() || precoS.isEmpty()) {

                JOptionPane.showMessageDialog(new JFrame(), "Não podem existir campos vazios", "Erro",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                boolean flag = true;
                if(!veiculos.get(i).getMatricula().equals(matricula)){
                    flag = VerifMatricula(matricula);
                }

                if(flag){

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

                        veiculos.set(i, c);

                        JOptionPane.showMessageDialog(new JFrame(), "Veículo editado com sucesso", "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);

                        Veiculos();
                        frame.dispose();

                    } catch (Exception ex) {

                        JOptionPane.showMessageDialog(new JFrame(), error, "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }

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

    public static void VeiculoD(int i){

        if(veiculos.get(i).isAlugado()){
            JOptionPane.showMessageDialog(new JFrame(), "Não é possível remover um veículo alugado", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }else{

            int reply = JOptionPane.showConfirmDialog(new JFrame(), "Deseja remover este veículo?", "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                veiculos.remove(i);
                JOptionPane.showMessageDialog(new JFrame(), "Veículo removido com sucesso", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                Veiculos();
            }

        }

    }

    public static void VeiculoS(){

        JFrame frame = new JFrame("Selecionar veículo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false); // torna o frame não redimensionável
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // centraliza o frame na tela

        // Título "Veículos"
        JLabel titleLabel = new JLabel("Veículo a alugar");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Define o estilo do título
        titleLabel.setBorder(new EmptyBorder(10, 10, 0, 0)); // Espaçamento externo
        frame.add(titleLabel, BorderLayout.NORTH);

        // JList scrollable
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(Veiculo v : veiculos){

            if(!v.isAlugado()){
                listModel.addElement(v.toString());
            }

        }

        JList<String> veiculosList = new JList<>(listModel);
        veiculosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(veiculosList);
        scrollPane.setBorder(new EmptyBorder(0, 10, 0, 0)); // Espaçamento externo
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botões à direita
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo


        JButton button1 = new JButton("Selecionar");
        JButton button2 = new JButton("Voltar");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedVeiculo = veiculosList.getSelectedIndex();
                ClienteS();
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

        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setVisible(true);

    }

    // Alugueis CRUD

    public static void AluguerC(){

        JFrame frame = new JFrame("Aluguer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 670);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centralizar o frame na tela

        // Título "Veículos"
        JLabel titleLabel = new JLabel("Detalhes do aluguer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Define o estilo do título
        titleLabel.setBorder(new EmptyBorder(10, 10, 0, 0)); // Espaçamento externo
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

        panel.add(new JLabel("<html><B>Veículo: </B><br><br>" +
                "<B>Marca: </B>" + veiculos.get(selectedVeiculo).getMarca() + "<br>" +
                "<B>Modelo: </B>" + veiculos.get(selectedVeiculo).getModelo() + "<br>" +
                "<B>Matrícula: </B>" + veiculos.get(selectedVeiculo).getMatricula() + "<br>" + "</html>"

        ));

        JButton veiculoButton = new JButton("Ver detalhes");

        panel.add(veiculoButton);

        panel.add(new JLabel("<html><B>Cliente: </B><br><br>" +
                "<B>Nome completo: </B>" + clientes.get(selectedCliente).getNome() + "<br>" +
                "<B>NIF: </B>" + clientes.get(selectedCliente).getNif() + "<br>" +
                "<B>Telemóvel: </B>" + clientes.get(selectedCliente).getTelemovel() + "<br>" + "</html>"

        ));

        JButton clienteButton = new JButton("Ver detalhes");

        panel.add(clienteButton);

        panel.add(new JLabel("Número do aluguer: " + (alugueis.size()+1)));

        panel.add(new JLabel("Data de início do aluguer: " + LocalDate.now()));

        JButton confirmButton = new JButton("Confirmar");
        JButton cancelButton = new JButton("Cancelar");

        veiculoButton.addActionListener(e -> {
            VeiculoR(selectedVeiculo);
        });

        clienteButton.addActionListener(e -> {
            ClienteR(selectedCliente);
        });

        cancelButton.addActionListener(e -> {
            int reply = JOptionPane.showConfirmDialog(new JFrame(), "Deseja cancelar este aluguer?", "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                Alugueis();
                frame.dispose();
            }

        });

        confirmButton.addActionListener(e -> {
            Aluguer a = new Aluguer((alugueis.size()+1), veiculos.get(selectedVeiculo), clientes.get(selectedCliente), LocalDate.now());
            alugueis.add(a);
            JOptionPane.showMessageDialog(new JFrame(), "Aluguer realizado com sucesso", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            Alugueis();
            frame.dispose();
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // 1 linha, 2 colunas
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    public static void AluguerR(int i){

        JFrame frame = new JFrame("Aluguer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 670);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centralizar o frame na tela

        // Título "Veículos"
        JLabel titleLabel = new JLabel("Detalhes do aluguer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Define o estilo do título
        titleLabel.setBorder(new EmptyBorder(10, 10, 0, 0)); // Espaçamento externo
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

        panel.add(new JLabel("<html><B>Veículo: </B><br><br>" +
                "<B>Marca: </B>" + alugueis.get(i).getV().getMarca() + "<br>" +
                "<B>Modelo: </B>" + alugueis.get(i).getV().getModelo() + "<br>" +
                "<B>Matrícula: </B>" + alugueis.get(i).getV().getMatricula() + "<br>" + "</html>"

        ));

        JButton veiculoButton = new JButton("Ver detalhes");

        panel.add(veiculoButton);

        panel.add(new JLabel("<html><B>Cliente: </B><br><br>" +
                "<B>Nome completo: </B>" + alugueis.get(i).getC().getNome() + "<br>" +
                "<B>NIF: </B>" + alugueis.get(i).getC().getNif() + "<br>" +
                "<B>Telemóvel: </B>" + alugueis.get(i).getC().getTelemovel() + "<br>" + "</html>"

        ));

        JButton clienteButton = new JButton("Ver detalhes");

        panel.add(clienteButton);

        panel.add(new JLabel("Número do aluguer: " + (i)));

        panel.add(new JLabel("Data de início do aluguer: " + alugueis.get(i).getInicio()));

        JButton confirmButton = new JButton("Terminar");
        JButton cancelButton = new JButton("Voltar");

        veiculoButton.addActionListener(e -> {
            VeiculoR(selectedVeiculo);
        });

        clienteButton.addActionListener(e -> {
            ClienteR(selectedCliente);
        });

        cancelButton.addActionListener(e -> {
            Alugueis();
            frame.dispose();
        });

        confirmButton.addActionListener(e -> {
            int reply = JOptionPane.showConfirmDialog(new JFrame(), "Deseja terminar este aluguer?", "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                alugueis.get(i).setTermidado(true);
                Alugueis();
                frame.dispose();
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

    // Clientes CRUD

    public static void ClienteC(){

        JFrame frame = new JFrame("Novo cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centralizar o frame na tela

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

        panel.add(new JLabel("Nome completo:"));
        JTextField txtNome = new JTextField("");
        panel.add(txtNome);

        panel.add(new JLabel("Data de nascimento:"));
        DatePicker dataNasc = new DatePicker();
        panel.add(dataNasc);

        panel.add(new JLabel("NIF:"));
        JTextField txtNIF = new JTextField("");
        panel.add(txtNIF);

        panel.add(new JLabel("Email:"));
        JTextField txtEmail = new JTextField("");
        panel.add(txtEmail);

        panel.add(new JLabel("Telemóvel:"));
        JTextField txtTelemovel = new JTextField("");
        panel.add(txtTelemovel);

        panel.add(new JLabel("Número da carta de condução:"));
        JTextField txtCarta = new JTextField("");
        panel.add(txtCarta);

        panel.add(new JLabel("Validade da carta de condução:"));
        DatePicker dataCarta = new DatePicker();
        panel.add(dataCarta);

        JButton confirmButton = new JButton("Confirmar");
        JButton cancelButton = new JButton("Voltar");

        cancelButton.addActionListener(e -> {
            ClienteS();
            frame.dispose();
        });

        confirmButton.addActionListener(e -> {

            String nome = txtNome.getText().trim();
            LocalDate dateNasc = dataNasc.getDate();
            String nif = txtNIF.getText().trim();
            String email = txtEmail.getText().trim();
            String telemovel = txtTelemovel.getText().trim();
            String carta = txtCarta.getText().trim();
            LocalDate dateCarta = dataCarta.getDate();

            if (nome.isEmpty() || dateNasc == null || nif.isEmpty() || email.isEmpty() || telemovel.isEmpty() || carta.isEmpty() || dateCarta == null) {

                JOptionPane.showMessageDialog(new JFrame(), "Não podem existir campos vazios", "Erro",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                String error = "Data de nascimento inválida";

                try {

                    Period period = Period.between(dateNasc, LocalDate.now());
                    if (!(period.getYears() >= 18 &&  period.getYears() <= 67)) {
                        Integer.parseInt("Error");
                    }

                    error = "NIF inválido";

                    if (!validaNif(nif)) {
                        Integer.parseInt("Error");
                    }

                    error = "Email inválido";

                    if (!validaEmail(email)) {
                        Integer.parseInt("Error");
                    }

                    error = "Telemóvel inválido";

                    if (!validaTele(telemovel)) {
                        Integer.parseInt("Error");
                    }

                    error = "Validade da carta de condução inválida";

                    if ((!dateCarta.isAfter(LocalDate.now()))) {
                        Integer.parseInt("Error");
                    }

                    Cliente c = new Cliente(nome, dateNasc, Integer.parseInt(nif), email, Integer.parseInt(telemovel), new CartaConducao(carta, dateCarta));

                    clientes.add(c);

                    JOptionPane.showMessageDialog(new JFrame(), "Cliente registado com sucesso", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);

                    int reply = JOptionPane.showConfirmDialog(new JFrame(), "Deseja selecionar este cliente?", "Sucesso",
                            JOptionPane.YES_NO_OPTION);

                    if (reply == JOptionPane.YES_OPTION) {
                        selectedCliente = clientes.size()-1;
                        AluguerC();
                        frame.dispose();
                    } else {
                        ClienteS();
                        frame.dispose();
                    }

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

    public static void ClienteR(int i){
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        JLabel myLabel = new JLabel();
        myLabel.setText(clientes.get(i).print());
        panel.add(myLabel);

        JOptionPane.showConfirmDialog(null, panel, "Detalhes do cliente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    public static void ClienteS(){

        JFrame frame = new JFrame("Selecionar cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false); // torna o frame não redimensionável
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // centraliza o frame na tela

        JLabel titleLabel = new JLabel("Cliente");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Define o estilo do título
        titleLabel.setBorder(new EmptyBorder(10, 10, 0, 0)); // Espaçamento externo
        frame.add(titleLabel, BorderLayout.NORTH);

        // JList scrollable
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(Cliente c : clientes){

            listModel.addElement(c.toString());

        }

        JList<String> clientesList = new JList<>(listModel);
        clientesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(clientesList);
        scrollPane.setBorder(new EmptyBorder(0, 10, 0, 0)); // Espaçamento externo
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botões à direita
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo


        JButton button1 = new JButton("Selecionar");
        JButton button2 = new JButton("Novo");
        JButton button3 = new JButton("Voltar");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCliente = clientesList.getSelectedIndex();
                AluguerC();
                frame.dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteC();
                frame.dispose();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VeiculoS();
                frame.dispose();
            }
        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setVisible(true);

    }

    public static boolean VerifMatricula(String m){

        boolean m1 = Pattern.matches("[0-9]{2}+-[A-Za-z]{2}+-[0-9]{2}",m);
        boolean m2 = Pattern.matches("[A-Za-z]{2}+-[0-9]{2}+-[A-Za-z]{2}",m);

        if(!m1 && !m2){

            JOptionPane.showMessageDialog(new JFrame(), "Matrícula inválida (formato 00-AA-00 ou AA-00-AA)", "Erro",
                    JOptionPane.ERROR_MESSAGE);

            return false;

        }else{

            for(Veiculo v : veiculos){
                if(v.getMatricula().equals(m)){

                    JOptionPane.showMessageDialog(new JFrame(), "Matrícula já existe", "Erro",
                            JOptionPane.ERROR_MESSAGE);

                    return false;

                }
            }

            return true;

        }

        // Tipos de matricula aceites: 00-AA-00 (2005-2020) || AA-00-AA (2020-atualmente)

        // Regex 00-AA-00: [0-9]{2}+-[A-Za-z]{2}+-[0-9]{2} && [A-Za-z]{2}+-[0-9]{2}+-[A-Za-z]{2}

        // Chamar quando utilizador insere a matricula ao registar um veiculo

        // Verificar primeiro a estrutura da matricula

        // Verificar se a matricula existe no array veiculos

    }

    public static boolean validaNif(String nif){

        /*
        As regras para a validação do NIF são:

        Tem de ter 9 dígitos;
        O primeiro dígito tem de ser 1, 2, 5, 6, 8 ou 9; (Esta é a informação que circula na maior parte dos fóruns da internet, mas a realidade é que o 3 está reservado para uso de particulares assim que os começados por 2 se esgotarem e o 4 e 7 são utilizados em casos especiais, pelo que, por omissão, a nossa função ignora esta validação)
        O dígito de controlo (último digíto do NIF) é obtido da seguinte forma:
        9*d1 + 8*d2 + 7*d3 + 6*d4 + 5*d5 + 4*d6 + 3*d7 + 2*d8 + 1*d9  (em que d1 a d9 são os 9 dígitos do NIF);
        Esta soma tem de ser múltiplo de 11 (quando divídida por 11 dar 0);
        Subtraír o resto da divisão da soma por 11 a 11;
        Se o resultado for 10, é assumído o algarismo 0;
        [in webdados]
        */

        try{
            final int max=9;
            if (!nif.matches("[0-9]+") || nif.length()!=max) return false;
            int checkSum=0;
            //calcula a soma de controlo
            for (int i=0; i<max-1; i++){
                checkSum+=(nif.charAt(i)-'0')*(max-i);
            }
            int checkDigit=11-(checkSum % 11);
            if (checkDigit>=10) checkDigit=0;
            return checkDigit==nif.charAt(max-1)-'0';
        }catch (Exception e) {
            return false;
        }
        finally
        {
        }

    }

    public static boolean validaEmail(String email){
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email);
    }

    public static boolean validaTele(String telemovel){

        return Pattern.matches("9[1236][0-9]{7}", telemovel);

    }

    public static void main(String[] args) {
        Inicio();
    }

}