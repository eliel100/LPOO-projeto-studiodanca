/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model.Modalidade;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.dao.PersistenciaJPA;

public class JpanelTelaModalidades extends JPanel implements ActionListener {
    private JList<Modalidade> lstModalidades;
    private DefaultListModel<Modalidade> listModel;
    private JButton btnNovo;
    private JButton btnEditar;
    private PersistenciaJPA persistencia;

    public JpanelTelaModalidades(List<Modalidade> modalidades) {
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Modalidades Cadastradas:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        listModel = new DefaultListModel<>();
        lstModalidades = new JList<>(listModel);
        lstModalidades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstModalidades.setVisibleRowCount(10);
        lstModalidades.setFixedCellWidth(300);

        JScrollPane scrollPane = new JScrollPane(lstModalidades);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        btnNovo = new JButton("Adicionar Nova Modalidade");
        btnNovo.setFont(new Font("Arial", Font.PLAIN, 14));
        btnNovo.setPreferredSize(new Dimension(200, 30));
        btnNovo.setMargin(new Insets(5, 10, 5, 10));
        btnNovo.addActionListener(this);

        btnEditar = new JButton("Editar Modalidade");
        btnEditar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnEditar.setPreferredSize(new Dimension(200, 30));
        btnEditar.setMargin(new Insets(5, 10, 5, 10));
        btnEditar.addActionListener(this);

        JPanel panelButtons = new JPanel();
        panelButtons.add(btnNovo);
        panelButtons.add(btnEditar);

        add(lblTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);

        if (modalidades == null || modalidades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma modalidade encontrada.");
        } else {
            carregarModalidades(modalidades);
        }

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
            String descricao = JOptionPane.showInputDialog(this, "Digite a descrição da nova modalidade:");

            if (descricao != null && !descricao.trim().isEmpty()) {
                try {
                    Modalidade novaModalidade = new Modalidade();
                    novaModalidade.setDescricao(descricao);

                    persistencia.conexaoAberta();
                    persistencia.persist(novaModalidade);
                    persistencia.fecharConexao();

                    listModel.addElement(novaModalidade);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao adicionar a nova modalidade: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Descrição inválida ou não fornecida.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == btnEditar) {
            Modalidade modalidadeSelecionada = lstModalidades.getSelectedValue();
            if (modalidadeSelecionada != null) {
                TelaCadastroModalidade telaCadastro = new TelaCadastroModalidade(
                        (Frame) SwingUtilities.getWindowAncestor(this), modalidadeSelecionada);
                telaCadastro.setVisible(true);

                // Atualiza a lista após a edição
                telaCadastro.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        atualizarListaModalidades();
                    }
                });
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma modalidade selecionada para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void atualizarListaModalidades() {
        try {
            persistencia.conexaoAberta();
            List<Modalidade> modalidades = persistencia.buscarTodasModalidades();
            listModel.clear();
            carregarModalidades(modalidades);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar a lista de modalidades: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            persistencia.fecharConexao();
        }
    }
}
