package org.example;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

        JLabel descricaoLabel = new JLabel("por Gonçalo Coval e Gonçalo Silva", SwingConstants.CENTER);
        descricaoLabel.setFont(new Font("Arial", Font.ITALIC, 12)); // define o estilo do título
        descricaoLabel.setBorder(new EmptyBorder(0, 0, 20, 0)); // padding bottom: 20px
        frame.add(descricaoLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton button1 = new JButton("Veículos");
        JButton button2 = new JButton("Alugueis");

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
        frame.setSize(800, 600);
        frame.setResizable(false); // torna o frame não redimensionável
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // centraliza o frame na tela

        // Título "Veículos"
        JLabel titleLabel = new JLabel("Veículos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Define o estilo do título
        titleLabel.setBorder(new EmptyBorder(15, 15, 15, 0)); // Espaçamento externo
        frame.add(titleLabel, BorderLayout.NORTH);

        // JList scrollable
        ArrayList<String> veiculosPositions = new ArrayList();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(Veiculo v : veiculos){
            listModel.addElement(v.toString());
            veiculosPositions.add(v.getMatricula());
        }

        JList<String> vehicleList = new JList<>(listModel);
        vehicleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(vehicleList);
        scrollPane.setBorder(new EmptyBorder(5, 10, 5, 10)); // Espaçamento externo
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botões à direita
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 0, 10)); // 6 linhas, 1 coluna
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

        // Combobox para filtrar categorias
        String[] categorias = {"Todos", "Económico", "XL", "Executivo", "Acessibilidade"};
        //buttonPanel.add(new JLabel("Categoria:"));
        JComboBox<String> comboCategorias = new JComboBox<>(categorias);
        comboCategorias.setSelectedIndex(0);
        buttonPanel.add(comboCategorias);

        comboCategorias.addActionListener((ActionEvent e) -> {
            listModel.removeAllElements();
            veiculosPositions.removeAll(veiculosPositions);
            for(Veiculo v : veiculos){
                if(comboCategorias.getSelectedIndex() == 0){
                    listModel.addElement(v.toString());
                    veiculosPositions.add(v.getMatricula());
                }else{
                    switch (comboCategorias.getSelectedIndex()){
                        case 1 -> {
                            if(v instanceof CarroEconomico){
                                listModel.addElement(v.toString());
                                veiculosPositions.add(v.getMatricula());
                            }
                        }
                        case 2 -> {
                            if(v instanceof CarroXL){
                                listModel.addElement(v.toString());
                                veiculosPositions.add(v.getMatricula());
                            }
                        }
                        case 3 -> {
                            if(v instanceof CarroExecutivo){
                                listModel.addElement(v.toString());
                                veiculosPositions.add(v.getMatricula());
                            }
                        }
                        case 4 -> {
                            if(v instanceof CarroAcessibilidade){
                                listModel.addElement(v.toString());
                                veiculosPositions.add(v.getMatricula());
                            }
                        }
                    }
                }
            }
            vehicleList.setModel(listModel);
        });

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
                    VeiculoR(veiculos.get(getVeiculoIndex(veiculosPositions.get(vehicleList.getSelectedIndex()))));
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
                    VeiculoU(getVeiculoIndex(veiculosPositions.get(vehicleList.getSelectedIndex())));
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
                    VeiculoD(getVeiculoIndex(veiculosPositions.get(vehicleList.getSelectedIndex())));
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
        frame.setSize(800, 600);
        frame.setResizable(false); // torna o frame não redimensionável
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // centraliza o frame na tela

        // Título "Veículos"
        JLabel titleLabel = new JLabel("Alugueis");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Define o estilo do título
        titleLabel.setBorder(new EmptyBorder(15, 15, 15, 0)); // Espaçamento externo
        frame.add(titleLabel, BorderLayout.NORTH);

        // JList scrollable
        DefaultListModel<String> listModel = new DefaultListModel<>();
        ArrayList<Integer> alugueisPositions = new ArrayList();

        for(Aluguer a : alugueis){

            if(!a.isTermidado()){
                listModel.addElement(a.toString());
                alugueisPositions.add(a.getNum() - 1);
            }

        }

        JList<String> alugueisList = new JList<>(listModel);
        alugueisList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(alugueisList);
        scrollPane.setBorder(new EmptyBorder(5, 10, 5, 10)); // Espaçamento externo
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
                        alugueisPositions.add(a.getNum() - 1);
                    }

                }else{

                    for(Aluguer a : alugueis){
                        if(!a.isTermidado()){
                            listModel.addElement(a.toString());
                            alugueisPositions.add(a.getNum() - 1);
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
                    if(!v.isAlugado() && v.isDocumentos()){
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
        frame.setSize(600, 500);
        frame.setResizable(false);
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

        panel.add(new JLabel("Velocidade máxima (km/h):"));
        JTextField txtVelocidade = new JTextField("");
        panel.add(txtVelocidade);

        panel.add(new JLabel("Combustível:"));
        JComboBox<String> comboCombustivel = new JComboBox<>(new String[]{"Gasolina", "Diesel", "Elétrico", "Híbrido"});
        panel.add(comboCombustivel);

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

            String marca = txtMarca.getText().trim().toUpperCase();
            String modelo = txtModelo.getText().trim().toUpperCase();
            String matricula = txtMatricula.getText().toUpperCase();
            String anoS = txtAno.getText();
            String portasS = txtPortas.getText();
            String capacidadeS = txtCapacidade.getText();
            String velocidadeS = txtVelocidade.getText();
            String combustivel = comboCombustivel.getSelectedItem().toString();
            String precoS = txtPreco.getText();
            boolean documentos = chkDocumentos.isSelected();
            boolean chkOne = chkVOne.isSelected();
            boolean chkTwo = chkVTwo.isSelected();

            if (marca.isEmpty() || modelo.isEmpty() || matricula.isEmpty() || anoS.isEmpty() || portasS.isEmpty() || capacidadeS.isEmpty() || velocidadeS.isEmpty() || precoS.isEmpty()) {

                JOptionPane.showMessageDialog(new JFrame(), "Não podem existir campos vazios!", "Erro",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                if(VerifMatricula(matricula)){

                    String error = "Ano inválido (veículo não pode ter mais de 7 anos)!";

                    try {

                        int ano = Integer.parseInt(anoS);
                        if ((ano > Year.now().getValue()) || (Year.now().getValue() - ano) > 7) {
                            Integer.parseInt("Error");
                        }

                        error = "Número de portas inválido (tem de ter entre 5 a 8 portas)!";
                        int portas = Integer.parseInt(portasS);
                        if (portas < 5 || portas > 8) {
                            Integer.parseInt("Error");
                        }

                        error = "Capacidade inválida (tem de ser entre 2 a 9 lugares)!";
                        int capacidade = Integer.parseInt(capacidadeS);
                        if (capacidade < 2 || capacidade > 9) {
                            Integer.parseInt("Error");
                        }

                        error = "Velocidade inválida (tem de ser entre 80 e 500 km/h)!";
                        double velocidade = Double.parseDouble(velocidadeS);
                        if (velocidade < 80 || velocidade > 500) {
                            Integer.parseInt("Error");
                        }

                        error = "Preço inválido!";
                        double preco = Double.parseDouble(precoS);
                        BigDecimal precoBD = new BigDecimal(preco).setScale(2, RoundingMode.HALF_UP);
                        preco = precoBD.doubleValue();
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

                        JOptionPane.showMessageDialog(new JFrame(), "Veículo registado com sucesso!", "Sucesso",
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

    public static void VeiculoR(Veiculo v){

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        JLabel myLabel = new JLabel();
        myLabel.setText(v.print());
        panel.add(myLabel);

        JOptionPane.showConfirmDialog(null, panel, "Detalhes do veículo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

    }

    public static void VeiculoU(int i){

        if(veiculos.get(i).isAlugado()){
            JOptionPane.showMessageDialog(new JFrame(), "Não é possível editar um veículo alugado!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            Veiculos();
        }else{

            JFrame frame = new JFrame("Editar veículo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);
            frame.setResizable(false);
            frame.setLayout(new BorderLayout());
            frame.setLocationRelativeTo(null); // Centralizar o frame na tela

            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
            panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

            panel.add(new JLabel("Categoria:"));
            String[] categorias = {"Económico", "XL", "Executivo", "Acessibilidade"};
            JComboBox<String> comboCategorias = new JComboBox<>(categorias);

            boolean flagchkOne = false;
            boolean flagchkTwo = false;
            JCheckBox chkVOne = new JCheckBox();
            JCheckBox chkVTwo = new JCheckBox();

            if(veiculos.get(i) instanceof CarroEconomico){
                flagchkOne = ((CarroEconomico) veiculos.get(i)).isBluetooth();
                flagchkTwo = ((CarroEconomico) veiculos.get(i)).isGps();
                chkVOne.setText("Bluetooth");
                chkVTwo.setText("Sistema de navegação");
                comboCategorias.setSelectedIndex(0);
            } else if (veiculos.get(i) instanceof CarroXL){
                flagchkOne = ((CarroXL) veiculos.get(i)).isEstacionamento();
                flagchkTwo = ((CarroXL) veiculos.get(i)).isTracao();
                chkVOne.setText("Sensores de estacionamento");
                chkVTwo.setText("Tração às 4 rodas");
                comboCategorias.setSelectedIndex(1);
            } else if (veiculos.get(i) instanceof CarroExecutivo){
                flagchkOne = ((CarroExecutivo) veiculos.get(i)).isWifi();
                flagchkTwo = ((CarroExecutivo) veiculos.get(i)).isMassagem();
                chkVOne.setText("WiFi");
                chkVTwo.setText("Assentos com massagem");
                comboCategorias.setSelectedIndex(2);
            } else if (veiculos.get(i) instanceof CarroAcessibilidade){
                flagchkOne = ((CarroAcessibilidade) veiculos.get(i)).isAuto();
                flagchkTwo = ((CarroAcessibilidade) veiculos.get(i)).isRampa();
                chkVOne.setText("Porta automática");
                chkVTwo.setText("Rampa para cadeira de rodas");
                comboCategorias.setSelectedIndex(3);
            }
            chkVOne.setSelected(flagchkOne);
            chkVTwo.setSelected(flagchkTwo);
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

            panel.add(new JLabel("Velocidade máxima (km/h):"));
            JTextField txtVelocidade = new JTextField(String.valueOf(veiculos.get(i).getVelocidade()));
            panel.add(txtVelocidade);

            panel.add(new JLabel("Combustível:"));
            JComboBox<String> comboCombustivel = new JComboBox<>(new String[]{"Gasolina", "Diesel", "Elétrico", "Híbrido"});
            if(veiculos.get(i).getCombustivel().equals("Gasolina")){
                comboCombustivel.setSelectedIndex(0);
            }else if(veiculos.get(i).getCombustivel().equals("Diesel")){
                comboCombustivel.setSelectedIndex(1);
            }else if(veiculos.get(i).getCombustivel().equals("Elétrico")){
                comboCombustivel.setSelectedIndex(2);
            }else{
                comboCombustivel.setSelectedIndex(3);
            }
            panel.add(comboCombustivel);

            panel.add(new JLabel("Preço por dia (€):"));
            JTextField txtPreco = new JTextField(String.valueOf(veiculos.get(i).getPreco()));
            panel.add(txtPreco);

            JCheckBox chkDocumentos = new JCheckBox("Documentos em ordem");
            chkDocumentos.setSelected(veiculos.get(i).isDocumentos());
            panel.add(chkDocumentos);

            panel.add(new JLabel(""));

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

                String marca = txtMarca.getText().trim().toUpperCase();
                String modelo = txtModelo.getText().trim().toUpperCase();
                String matricula = txtMatricula.getText().toUpperCase();
                String anoS = txtAno.getText();
                String portasS = txtPortas.getText();
                String capacidadeS = txtCapacidade.getText();
                String velocidadeS = txtVelocidade.getText();
                String combustivel = comboCombustivel.getSelectedItem().toString();
                String precoS = txtPreco.getText();
                boolean documentos = chkDocumentos.isSelected();
                boolean chkOne = chkVOne.isSelected();
                boolean chkTwo = chkVTwo.isSelected();

                if (marca.isEmpty() || modelo.isEmpty() || matricula.isEmpty() || anoS.isEmpty() || portasS.isEmpty() || capacidadeS.isEmpty() || velocidadeS.isEmpty() || precoS.isEmpty()) {

                    JOptionPane.showMessageDialog(new JFrame(), "Não podem existir campos vazios!", "Erro",
                            JOptionPane.ERROR_MESSAGE);

                } else {

                    boolean flag = true;
                    if(!veiculos.get(i).getMatricula().equals(matricula)){
                        flag = VerifMatricula(matricula);
                    }

                    if(flag){

                        String error = "Ano inválido (veículo não pode ter mais de 7 anos)!";

                        try {

                            int ano = Integer.parseInt(anoS);
                            if ((ano > Year.now().getValue()) || (Year.now().getValue() - ano) > 7) {
                                Integer.parseInt("Error");
                            }

                            error = "Número de portas inválido (tem de ter entre 5 a 8 portas)!";
                            int portas = Integer.parseInt(portasS);
                            if (portas < 5 || portas > 10) {
                                Integer.parseInt("Error");
                            }

                            error = "Capacidade inválida (tem de ser entre 2 a 9 lugares)!";
                            int capacidade = Integer.parseInt(capacidadeS);
                            if (capacidade < 1 || capacidade > 9) {
                                Integer.parseInt("Error");
                            }

                            error = "Velocidade inválida (tem de ser entre 80 e 500 km/h)!";
                            double velocidade = Double.parseDouble(velocidadeS);
                            if (velocidade < 50 || velocidade > 500) {
                                Integer.parseInt("Error");
                            }

                            error = "Preço inválido!";
                            double preco = Double.parseDouble(precoS);
                            BigDecimal precoBD = new BigDecimal(preco).setScale(2, RoundingMode.HALF_UP);
                            preco = precoBD.doubleValue();
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

                            JOptionPane.showMessageDialog(new JFrame(), "Veículo editado com sucesso!", "Sucesso",
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

    }

    public static void VeiculoD(int i){

        if(veiculos.get(i).isAlugado()){
            JOptionPane.showMessageDialog(new JFrame(), "Não é possível remover um veículo alugado!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            Veiculos();
        }else{

            int reply = JOptionPane.showConfirmDialog(new JFrame(), "Deseja remover este veículo?", "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                veiculos.remove(i);
                JOptionPane.showMessageDialog(new JFrame(), "Veículo removido com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            Veiculos();

        }

    }

    public static void VeiculoS(){

        JFrame frame = new JFrame("Selecionar veículo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false); // torna o frame não redimensionável
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // centraliza o frame na tela

        // Título "Veículos"
        JLabel titleLabel = new JLabel("Veículo a alugar");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Define o estilo do título
        titleLabel.setBorder(new EmptyBorder(15, 15, 15, 0)); // Espaçamento externo
        frame.add(titleLabel, BorderLayout.NORTH);

        // JList scrollable
        ArrayList<String> veiculosPositions = new ArrayList();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(Veiculo v : veiculos){
            if(!v.isAlugado() && v.isDocumentos()){
                listModel.addElement(v.toString());
                veiculosPositions.add(v.getMatricula());
            }
        }

        JList<String> vehicleList = new JList<>(listModel);
        vehicleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(vehicleList);
        scrollPane.setBorder(new EmptyBorder(5, 10, 5, 10)); // Espaçamento externo
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botões à direita
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

        // Combobox para filtrar categorias
        String[] categorias = {"Todos", "Económico", "XL", "Executivo", "Acessibilidade"};
        JComboBox<String> comboCategorias = new JComboBox<>(categorias);
        comboCategorias.setSelectedIndex(0);
        buttonPanel.add(comboCategorias);

        comboCategorias.addActionListener((ActionEvent e) -> {
            listModel.removeAllElements();
            veiculosPositions.removeAll(veiculosPositions);
            for(Veiculo v : veiculos){
                if(comboCategorias.getSelectedIndex() == 0){
                    listModel.addElement(v.toString());
                    veiculosPositions.add(v.getMatricula());
                }else{
                    switch (comboCategorias.getSelectedIndex()){
                        case 1 -> {
                            if(v instanceof CarroEconomico){
                                listModel.addElement(v.toString());
                                veiculosPositions.add(v.getMatricula());
                            }
                        }
                        case 2 -> {
                            if(v instanceof CarroXL){
                                listModel.addElement(v.toString());
                                veiculosPositions.add(v.getMatricula());
                            }
                        }
                        case 3 -> {
                            if(v instanceof CarroExecutivo){
                                listModel.addElement(v.toString());
                                veiculosPositions.add(v.getMatricula());
                            }
                        }
                        case 4 -> {
                            if(v instanceof CarroAcessibilidade){
                                listModel.addElement(v.toString());
                                veiculosPositions.add(v.getMatricula());
                            }
                        }
                    }
                }
            }
            vehicleList.setModel(listModel);
        });

        JButton button1 = new JButton("Selecionar");
        JButton button3 = new JButton("Detalhes");
        JButton button2 = new JButton("Voltar");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vehicleList.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Nenhum veículo selecionado!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else {
                    selectedVeiculo = getVeiculoIndex(veiculosPositions.get(vehicleList.getSelectedIndex()));
                    ClienteS();
                    frame.dispose();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alugueis();
                frame.dispose();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vehicleList.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(new JFrame(), "Nenhum veículo selecionado!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    VeiculoR(veiculos.get(getVeiculoIndex(veiculosPositions.get(vehicleList.getSelectedIndex()))));
                }

            }
        });

        buttonPanel.add(button1);
        buttonPanel.add(button3);
        buttonPanel.add(button2);

        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setVisible(true);

    }

    // Alugueis CRUD

    public static void AluguerC(){

        JFrame frame = new JFrame("Aluguer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 670);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centralizar o frame na tela

        // Título "Veículos"
        JLabel titleLabel = new JLabel("Detalhes do aluguer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Define o estilo do título
        titleLabel.setBorder(new EmptyBorder(10, 10, 0, 0)); // Espaçamento externo
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
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

        panel.add(new JLabel("Aluguer nº: " + (alugueis.size()+1)));

        panel.add(new JLabel("Data de início: " + LocalDate.now()));

        panel.add(new JLabel("Preço por dia: " + veiculos.get(selectedVeiculo).getPreco() + "€"));

        JButton confirmButton = new JButton("Confirmar");
        JButton cancelButton = new JButton("Cancelar");

        veiculoButton.addActionListener(e -> {
            VeiculoR(veiculos.get(selectedVeiculo));
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
            veiculos.get(selectedVeiculo).setAlugado(true);
            Aluguer a = new Aluguer((alugueis.size()+1), veiculos.get(selectedVeiculo), clientes.get(selectedCliente), LocalDate.now());
            alugueis.add(a);
            JOptionPane.showMessageDialog(new JFrame(), "Aluguer realizado com sucesso!", "Sucesso",
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
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centralizar o frame na tela

        // Título "Veículos"
        JLabel titleLabel = new JLabel("Detalhes do aluguer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Define o estilo do título
        titleLabel.setBorder(new EmptyBorder(10, 10, 0, 0)); // Espaçamento externo
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
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

        panel.add(new JLabel("Aluguer nº: " + (alugueis.get(i).getNum())));
        panel.add(new JLabel("Data de início: " + alugueis.get(i).getInicio()));

        JButton confirmButton = new JButton();
        JButton cancelButton = new JButton("Voltar");

        if(alugueis.get(i).isTermidado()){
            confirmButton.setText("Recibo");
            panel.add(new JLabel("Data de fim: " + alugueis.get(i).getFim()));
            panel.add(new JLabel("Preço total: " + alugueis.get(i).PrecoTotal() + "€"));
        }else{
            panel.add(new JLabel("Preço por dia: " + alugueis.get(i).getV().getPreco() + "€"));
            confirmButton.setText("Terminar");
        }

        veiculoButton.addActionListener(e -> {
            VeiculoR(alugueis.get(i).getV());
        });

        clienteButton.addActionListener(e -> {
            ClienteR(getClienteIndex(alugueis.get(i).getC().getNif()));
        });

        cancelButton.addActionListener(e -> {
            Alugueis();
            frame.dispose();
        });

        confirmButton.addActionListener(e -> {
            if(alugueis.get(i).isTermidado()){
                JOptionPane.showMessageDialog(new JFrame(), alugueis.get(i).Recibo(), "Recibo do aluguer",
                        JOptionPane.INFORMATION_MESSAGE);
            }else{
                int reply = JOptionPane.showConfirmDialog(new JFrame(), "Deseja terminar este aluguer?", "Confirmação",
                        JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {
                    alugueis.get(i).setFim(LocalDate.now());
                    alugueis.get(i).setTermidado(true);
                    veiculos.get(getVeiculoIndex(alugueis.get(i).getV().getMatricula())).setAlugado(false);
                    frame.dispose();
                    JOptionPane.showMessageDialog(new JFrame(), alugueis.get(i).Recibo(), "Recibo do aluguer",
                            JOptionPane.INFORMATION_MESSAGE);
                    Alugueis();
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

    // Clientes CRUD

    public static void ClienteC(){

        JFrame frame = new JFrame("Novo cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(false);
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

            String nome = txtNome.getText().trim().toUpperCase();
            LocalDate dateNasc = dataNasc.getDate();
            String nif = txtNIF.getText().trim();
            String email = txtEmail.getText().trim();
            String telemovel = txtTelemovel.getText().trim();
            String carta = txtCarta.getText().trim().toUpperCase();
            LocalDate dateCarta = dataCarta.getDate();

            if (nome.isEmpty() || dateNasc == null || nif.isEmpty() || email.isEmpty() || telemovel.isEmpty() || carta.isEmpty() || dateCarta == null) {

                JOptionPane.showMessageDialog(new JFrame(), "Não podem existir campos vazios!", "Erro",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                String error = "Data de nascimento inválida (tem de ter no mínimo 18 anos)!";

                try {

                    Period period = Period.between(dateNasc, LocalDate.now());
                    if (!(period.getYears() >= 18 &&  period.getYears() <= 67)) {
                        Integer.parseInt("Error");
                    }

                    error = "NIF inválido!";

                    int validarNif = validaNif(nif);
                    if(validarNif == 0) {
                        Integer.parseInt("Error");
                    }else if(validarNif == 1){
                        error = "NIF já existe!";
                        Integer.parseInt("Error");
                    }

                    error = "Email inválido!";

                    if (!validaEmail(email)) {
                        Integer.parseInt("Error");
                    }

                    error = "Telemóvel inválido!";

                    if (!validaTele(telemovel)) {
                        Integer.parseInt("Error");
                    }

                    error = "Carta já existente noutro cliente!";

                    if (!validaCarta(carta)) {
                        Integer.parseInt("Error");
                    }

                    error = "Validade da carta de condução inválida!";

                    if ((!dateCarta.isAfter(LocalDate.now()))) {
                        Integer.parseInt("Error");
                    }

                    Cliente c = new Cliente(nome, dateNasc, Integer.parseInt(nif), email, Integer.parseInt(telemovel), new CartaConducao(carta, dateCarta));

                    clientes.add(c);

                    JOptionPane.showMessageDialog(new JFrame(), "Cliente registado com sucesso!", "Sucesso",
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
        frame.setSize(800, 600);
        frame.setResizable(false); // torna o frame não redimensionável
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // centraliza o frame na tela

        JLabel titleLabel = new JLabel("Cliente");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Define o estilo do título
        titleLabel.setBorder(new EmptyBorder(15, 15, 15, 0)); // Espaçamento externo
        frame.add(titleLabel, BorderLayout.NORTH);

        // JList scrollable
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(Cliente c : clientes){

            listModel.addElement(c.toString());

        }

        JList<String> clientesList = new JList<>(listModel);
        clientesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(clientesList);
        scrollPane.setBorder(new EmptyBorder(5, 10, 5, 10)); // Espaçamento externo
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botões à direita
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 0, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

        JButton pesquisar = new JButton("Pesquisa por NIF");

        pesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nif = JOptionPane.showInputDialog("Insira o NIF do cliente");
                if(nif != null){
                    try{
                        boolean flag = false;
                        int sNIF = Integer.parseInt(nif);

                        for(Cliente c : clientes){
                            if(c.getNif() == sNIF){
                                flag = true;
                                int reply = JOptionPane.showConfirmDialog(new JFrame(), "Cliente encontrado! Deseja selecionar o cliente "+c.getNome()+"?", "Confirmação",
                                        JOptionPane.YES_NO_OPTION);
                                if(reply == JOptionPane.YES_OPTION){
                                    selectedCliente = getClienteIndex(c.getNif());
                                    AluguerC();
                                    frame.dispose();
                                }
                                break;
                            }
                        }

                        if(!flag){
                            JOptionPane.showMessageDialog(new JFrame(), "Cliente não encontrado!", "Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(new JFrame(), "NIF inválido!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        JButton button1 = new JButton("Selecionar");
        JButton button4 = new JButton("Detalhes");
        JButton button2 = new JButton("Novo");
        JButton button3 = new JButton("Voltar");
        JButton button5 = new JButton("Editar");

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clientesList.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(new JFrame(), "Nenhum cliente selecionado!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    ClienteU(clientesList.getSelectedIndex());
                    frame.dispose();
                }
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clientesList.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(new JFrame(), "Nenhum cliente selecionado!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    selectedCliente = clientesList.getSelectedIndex();
                    AluguerC();
                    frame.dispose();
                }
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

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clientesList.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(new JFrame(), "Nenhum cliente selecionado!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    ClienteR(clientesList.getSelectedIndex());
                }

            }
        });

        buttonPanel.add(pesquisar);
        buttonPanel.add(button2);
        buttonPanel.add(button1);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button3);

        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setVisible(true);

    }

    public static void ClienteU(int i) {

        JFrame frame = new JFrame("Editar cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centralizar o frame na tela

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

        panel.add(new JLabel("Nome completo:"));
        JTextField txtNome = new JTextField(clientes.get(i).getNome());
        panel.add(txtNome);

        panel.add(new JLabel("Data de nascimento:"));
        DatePicker dataNasc = new DatePicker();
        dataNasc.setDate(clientes.get(i).getData());
        panel.add(dataNasc);

        panel.add(new JLabel("NIF:"));
        JTextField txtNIF = new JTextField(String.valueOf(clientes.get(i).getNif()));
        panel.add(txtNIF);

        panel.add(new JLabel("Email:"));
        JTextField txtEmail = new JTextField(clientes.get(i).getEmail());
        panel.add(txtEmail);

        panel.add(new JLabel("Telemóvel:"));
        JTextField txtTelemovel = new JTextField(String.valueOf(clientes.get(i).getTelemovel()));
        panel.add(txtTelemovel);

        panel.add(new JLabel("Número da carta de condução:"));
        JTextField txtCarta = new JTextField(clientes.get(i).getCarta().getNumero());
        panel.add(txtCarta);

        panel.add(new JLabel("Validade da carta de condução:"));
        DatePicker dataCarta = new DatePicker();
        dataCarta.setDate(clientes.get(i).getCarta().getValidade());
        panel.add(dataCarta);

        JButton confirmButton = new JButton("Editar");
        JButton cancelButton = new JButton("Cancelar");

        cancelButton.addActionListener(e -> {
            ClienteS();
            frame.dispose();
        });

        confirmButton.addActionListener(e -> {

            String nome = txtNome.getText().trim().toUpperCase();
            LocalDate dateNasc = dataNasc.getDate();
            String nif = txtNIF.getText().trim();
            String email = txtEmail.getText().trim();
            String telemovel = txtTelemovel.getText().trim();
            String carta = txtCarta.getText().trim().toUpperCase();
            LocalDate dateCarta = dataCarta.getDate();


            if (nome.isEmpty() || dateNasc == null || nif.isEmpty() || email.isEmpty() || telemovel.isEmpty() || carta.isEmpty() || dateCarta == null) {

                JOptionPane.showMessageDialog(new JFrame(), "Não podem existir campos vazios!", "Erro",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                String error = "Data de nascimento inválida (tem de ter no mínimo 18 anos)!";

                try {

                    Period period = Period.between(dateNasc, LocalDate.now());
                    if (!(period.getYears() >= 18 && period.getYears() <= 67)) {
                        Integer.parseInt("Error");
                    }

                    error = "NIF inválido!";

                    if(clientes.get(i).getNif() != Integer.parseInt(nif)){

                        int validarNif = validaNif(nif);
                        if(validarNif == 0) {
                            Integer.parseInt("Error");
                        }else if(validarNif == 1){
                            error = "NIF já existe!";
                            Integer.parseInt("Error");
                        }

                    }

                    error = "Email inválido!";

                    if (!validaEmail(email)) {
                        Integer.parseInt("Error");
                    }

                    error = "Telemóvel inválido!";

                    if (!validaTele(telemovel)) {
                        Integer.parseInt("Error");
                    }

                    error = "Carta já existente noutro cliente!";

                    if(!(clientes.get(i).getCarta().getNumero().equals(carta))) {
                        if (!validaCarta(carta)) {
                            Integer.parseInt("Error");
                        }
                    }

                    error = "Validade da carta de condução inválida!";

                    if ((!dateCarta.isAfter(LocalDate.now()))) {
                        Integer.parseInt("Error");
                    }

                    Cliente c = new Cliente(nome, dateNasc, Integer.parseInt(nif), email, Integer.parseInt(telemovel), new CartaConducao(carta, dateCarta));

                    clientes.set(i, c);

                    JOptionPane.showMessageDialog(new JFrame(), "Cliente editado com sucesso!", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);

                    ClienteS();
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

    // Validações e métodos auxiliares

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

    public static int validaNif(String nif){

        // 0 - NIF inválido; 1 - NIF já existe; 2 - NIF válido

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

            for(Cliente c : clientes){
                if(c.getNif() == Integer.parseInt(nif)){
                    return 1;
                }
            }

            final int max=9;
            if (!nif.matches("[0-9]+") || nif.length()!=max) return 0;
            int checkSum=0;
            //calcula a soma de controlo
            for (int i=0; i<max-1; i++){
                checkSum+=(nif.charAt(i)-'0')*(max-i);
            }
            int checkDigit=11-(checkSum % 11);
            if (checkDigit>=10) checkDigit=0;
            boolean flag = checkDigit==nif.charAt(max-1)-'0';
            if (!flag) return 0;
            return 2;
        }catch (Exception e) {
            return 0;
        }
        finally
        {
        }

    }

    public static boolean validaEmail(String email){
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email);
    }

    public static boolean validaCarta(String carta){
        for(Cliente c : clientes){
            if(c.getCarta().getNumero().equals(carta)){
                return false;
            }
        }
        return true;
    }

    public static boolean validaTele(String telemovel){

        return Pattern.matches("9[1236][0-9]{7}", telemovel);

    }

    public static int getVeiculoIndex(String matricula){
        for(int i = 0; i < veiculos.size(); i++){
            if(veiculos.get(i).getMatricula().equals(matricula)){
                return i;
            }
        }
        return -1;
    }

    public static int getClienteIndex(int nif){
        for(int i = 0; i < clientes.size(); i++){
            if(clientes.get(i).getNif() == nif){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Inicio();
    }

}