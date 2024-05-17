import com.github.lgooddatepicker.components.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.*;
import org.example.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author goncalocoval
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    
    static ArrayList<Veiculo> veiculos = new ArrayList<>();
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Aluguer> alugueis = new ArrayList<>();
    
    public Main() {
        initComponents();
        jPanel1.setVisible(true);
        jPanel2.setVisible(false);
    }
    
    // Veiculo CRUD
    
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

                    // Aqui você pode adicionar a lógica para o que fazer com os dados do veículo
                    System.out.println("Marca: " + marca);
                    System.out.println("Modelo: " + modelo);
                    System.out.println("Matrícula: " + matricula);
                    System.out.println("Ano: " + ano);
                    System.out.println("Portas: " + portas);
                    System.out.println("Capacidade: " + capacidade);
                    System.out.println("Velocidade máxima: " + velocidade);
                    System.out.println("Combustível: " + combustivel);
                    System.out.println("Preço por dia: " + preco);
                    System.out.println("Documentos em ordem: " + documentos);
                    System.out.println("Opção 1: " + chkOne);
                    System.out.println("Opção 2: " + chkTwo);

                    JOptionPane.showMessageDialog(new JFrame(), "Veículo registado com sucesso", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(new JFrame(), error, "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.add(confirmButton, BorderLayout.SOUTH);

        frame.setVisible(true);
        
    }
    
    private static void VeiculoR(){
        
        // Receber veículo selecionado (matrícula || posição)
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        JLabel myLabel = new JLabel();
        //myLabel.setText(veiculos.get(pos).print());
        // IDEIA - Obter selected item diretamente na lista -> sem parametros... impasse: fazer por code jframe
        Veiculo teste = new CarroExecutivo();
        myLabel.setText(teste.print());
        panel.add(myLabel);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Detalhes do veículo",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        
    }
    
    private static void VeiculoD(){
        
        // Receber veículo selecionado
        
        // Verificar se está alugado
        
        // Dialog de confirmação -> OK -> Apagar -> Atualizar posições...
        
    }
    
    // Cliente CRUD
    
    private static void ClienteC() {
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        
        panel.add(new JLabel("Nome completo:"));
        JTextField txtNome = new JTextField("");
        panel.add(txtNome);
        
        panel.add(new JLabel("Data de nascimento:"));
        DatePicker dpData = new DatePicker();
        panel.add(dpData);
        
        panel.add(new JLabel("NIF:"));
        JTextField txtNIF = new JTextField("");
        panel.add(txtNIF);
        
        panel.add(new JLabel("Email:"));
        JTextField txtEmail = new JTextField("");
        panel.add(txtEmail);
        
        panel.add(new JLabel("Telefone:"));
        JTextField txtTelefone = new JTextField("");
        panel.add(txtTelefone);
        
        panel.add(new JLabel("Número da carta de condução:"));
        JTextField txtCarta = new JTextField("");
        panel.add(txtCarta);
        
        panel.add(new JLabel("Validade da carta de condução:"));
        DatePicker dpCarta = new DatePicker();
        panel.add(dpCarta);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Novo cliente",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        
        if (result == JOptionPane.OK_OPTION) {
            
            Cliente c = new Cliente();
            clientes.add(c);
            
            JOptionPane.showMessageDialog(new JFrame(), "Cliente criado com suceso", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            
        }
        
    }
    
    // Interface
    
    /*
    private static void Inicio(){
        
        JFrame frame = new JFrame("Aluguer de carros TVDE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setResizable(false); // torna o frame não redimensionável
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // centraliza o frame na tela

        // Adicionando um espaço entre o topo do frame e o título
        JLabel titleLabel = new JLabel("Aluguer de carros TVDE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // define o estilo do título
        titleLabel.setBorder(new EmptyBorder(20, 0, 0, 0)); // padding top: 20px
        frame.add(titleLabel, BorderLayout.NORTH);

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Botões
        JButton button1 = new JButton("Veículos");
        JButton button2 = new JButton("Aluguer");
        
        // Adicionando funcionalidades aos botões
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Teste();
                frame.dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Função a ser chamada quando o botão 2 for clicado
                System.out.println("Aluguer");
                // Adicione sua lógica aqui
                frame.dispose();
            }
        });

        // Adicionando os botões ao painel
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Adicionando um espaço entre o fundo do frame e o painel de botões
        buttonPanel.setBorder(new EmptyBorder(0, 0, 20, 0)); // padding bottom: 20px

        // Adicionando o painel de botões ao frame
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        
    }
    
    private static void Teste(){
        
        JFrame frame = new JFrame();
        frame.setTitle("Veículos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centralizar o frame na tela

        // Título "Veículos" no topo do frame
        JLabel titleLabel = new JLabel("Veículos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(new EmptyBorder(10, 10, 0, 0)); // Espaçamento externo
        frame.add(titleLabel, BorderLayout.NORTH);


        // JList
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> vehicleList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(vehicleList);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botões à direita
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 0, 10)); // 5 linhas, 1 coluna
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento externo

        JButton button1 = new JButton("Botão 1");
        JButton button2 = new JButton("Botão 2");
        JButton button3 = new JButton("Botão 3");
        JButton button4 = new JButton("Botão 4");
        JButton button5 = new JButton("Botão 5");

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);

        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setVisible(true);
        
    }
    */
    
    public void PanelOpen(int p){
        
        ArrayList<JPanel> panels = new ArrayList<>();
        panels.add(jPanel1);
        panels.add(jPanel2);
        panels.add(jPanel3);
        
        
        for(JPanel panel : panels){
            panel.setVisible(false);
        }
        
        panels.get(p-1).setVisible(true);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aluguer de carros TVDE");
        setResizable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel2.setText("Veículos");

        jButton3.setText("Registar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Editar");

        jButton5.setText("Remover");

        jButton6.setText("Voltar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextField1.setText("Pesquisar...");

        jButton7.setText("Detalhes");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(605, 605, 605))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addGap(49, 49, 49)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(40, 40, 40)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(109, 109, 109)
                        .addComponent(jButton6))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Aluguer de carros TVDE");

        jButton1.setText("Veículos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Alugueis");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 183, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(185, 185, 185))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(224, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jPanel1.setVisible(false);
        
        jPanel2.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        jPanel2.setVisible(false);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
