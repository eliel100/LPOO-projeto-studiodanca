/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author vanessalagomachado
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Column(length = 100, name = "pessoa_nome")
    private String nome;
    
    @Column(length = 17, name = "pessoa_telefone")
    private String fone;
    
    @Column(name="pessoa_data_aniversario")
    private Calendar dataAniver;
    
    @Column(name="pessoa_email") // Não usar hífen para armazenamento no BD 
    private String email;
    
    @Column(name="pessoa_endereco")
    private String endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone.replaceAll(" ", "");
    }

    public Calendar getDataAniver() {
        return dataAniver;
    }

    public void setDataAniver(Calendar dataAniver) {
        this.dataAniver = dataAniver;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }
    
    
    
}
