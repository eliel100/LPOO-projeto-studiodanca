/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.view;

import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.dao.PersistenciaJPA;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model.Modalidade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroModalidade extends JDialog {

    private Modalidade modalidade;
    private JTextField txtDescricao;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private PersistenciaJPA persistencia;

    public TelaCadastroModalidade(Frame owner) {
        super(owner, "Cadastro de Modalidade", true);
        this.persistencia = new PersistenciaJPA();
        initialize();
    }

    public TelaCadastroModalidade(Frame owner, Modalidade modalidade) {
        this(owner);
        setModalidade(modalidade);
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
        if (modalidade != null) {
            txtDescricao.setText(modalidade.getDescricao());
        }
    }

    private void initialize() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblDescricao = new JLabel("Descrição:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblDescricao, gbc);

        txtDescricao = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtDescricao, gbc);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarModalidade();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(btnSalvar, gbc);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(btnCancelar, gbc);

        pack();
        setLocationRelativeTo(null);
    }

    private void salvarModalidade() {
        if (modalidade != null) {
            modalidade.setDescricao(txtDescricao.getText());

            try {
                persistencia.conexaoAberta();
                persistencia.persist(modalidade); // Usa persist para atualizar ou criar
                JOptionPane.showMessageDialog(this, "Modalidade salva com sucesso.");
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar a modalidade: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                persistencia.fecharConexao();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhuma modalidade selecionada para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}

