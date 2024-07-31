/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model.Modalidade;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.dao.PersistenciaJPA;

public class JpanelTelaModalidades extends JPanel implements ActionListener {
    private JList<Modalidade> lstModalidades;
    private DefaultListModel<Modalidade> listModel;
    private JButton btnNovo;
    private PersistenciaJPA persistencia;

    public JpanelTelaModalidades(List<Modalidade> modalidades) {
        // Configura o layout do JPanel
        setLayout(new BorderLayout());

        // Cria o JLabel para o título
        JLabel lblTitulo = new JLabel("Modalidades Cadastradas:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16)); // Define a fonte do título
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona uma borda ao redor do título

        // Inicializa o DefaultListModel e o JList
        listModel = new DefaultListModel<>();
        lstModalidades = new JList<>(listModel);
        lstModalidades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Define o modo de seleção
        lstModalidades.setVisibleRowCount(10); // Define o número visível de linhas
        lstModalidades.setFixedCellWidth(300); // Define a largura das células

        // Adiciona o JList dentro de um JScrollPane
        JScrollPane scrollPane = new JScrollPane(lstModalidades);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove a borda padrão do JScrollPane

        // Cria o JButton para adicionar uma nova modalidade
        btnNovo = new JButton("Adicionar Nova Modalidade");
        btnNovo.setFont(new Font("Arial", Font.PLAIN, 14)); // Define a fonte do botão
        btnNovo.setPreferredSize(new Dimension(200, 30)); // Define o tamanho preferido do botão
        btnNovo.setMargin(new Insets(5, 10, 5, 10)); // Define as margens internas do botão
        btnNovo.addActionListener(this); // Adiciona o ActionListener ao botão

        // Adiciona o JLabel, JScrollPane e JButton ao JPanel
        add(lblTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnNovo, BorderLayout.SOUTH); // Adiciona o botão na parte inferior

        // Verifica se a lista de modalidades não está vazia
        if (modalidades == null || modalidades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma modalidade encontrada.");
        } else {
            // Carrega as modalidades no modelo do JList
            carregarModalidades(modalidades);
        }

        // Inicializa a PersistenciaJPA
        persistencia = new PersistenciaJPA();
    }

    private void carregarModalidades(List<Modalidade> modalidades) {
        for (Modalidade modalidade : modalidades) {
            listModel.addElement(modalidade);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNovo) {
            // Solicita a descrição da nova modalidade
            String descricao = JOptionPane.showInputDialog(this, "Digite a descrição da nova modalidade:");

            if (descricao != null && !descricao.trim().isEmpty()) {
                try {
                    // Cria uma nova modalidade
                    Modalidade novaModalidade = new Modalidade();
                    novaModalidade.setDescricao(descricao);

                    // Abre a conexão, persiste a nova modalidade e fecha a conexão
                    persistencia.conexaoAberta();
                    persistencia.persist(novaModalidade);
                    persistencia.fecharConexao();

                    // Atualiza a lista de modalidades
                    listModel.addElement(novaModalidade);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao adicionar a nova modalidade: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Descrição inválida ou não fornecida.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

