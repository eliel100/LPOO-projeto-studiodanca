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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author vanessalagomachado
 */
@Entity
@Table(name = "tb_folha_pagamento")
public class FolhaPagamento implements Serializable{
    @Id
    @Column(name = "folha_pgto_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Column(name = "folha_pgto_data_pgto")
    private Calendar dataPgto;
    
    @Column(name="folha_pgto_valor")
    private double valorReceber;
    
    
    //    Validação do Mapeamento Bidirecional: Garantir que o mapeamento @OneToMany em Professor 
//    e @ManyToOne em FolhaPagamento estejam corretamente configurados para refletir o relacionamento bidirecional.
    @ManyToOne
    @JoinColumn(name="folha_pgto_professor")
    private Professor professor;

    public Calendar getDataPgto() {
        return dataPgto;
    }

    public void setDataPgto(Calendar dataPgto) {
        this.dataPgto = dataPgto;
    }

    public double getValorReceber() {
        return valorReceber;
    }

    public void setValorReceber(double valorReceber) {
        this.valorReceber = valorReceber;
    }
    
    public void calcularFolhaMes(){
    
    }
    
}
