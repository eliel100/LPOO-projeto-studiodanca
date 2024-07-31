/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author vanessalagomachado
 */
@Entity
@Table(name = "tb_pagamento")
public class Pagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "pagamento_id")
    private Integer id;

    @Column(name = "pagamento_data_vcmto", nullable = false)
    private Calendar dataVcto;

    @Column(name = "pagamento_valor")
    private Double valor;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "pagamento_data_pgto")
    private Calendar dataPgto;

    @Column(name = "pagamento_valor_pgto", precision = 2)
    private Double valorPgto;

    @ManyToOne
    @JoinColumn(name="contrato_id")
    private Contrato contrato;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDataVcto() {
        return dataVcto;
    }

    public void setDataVcto(Calendar dataVcto) {
        this.dataVcto = dataVcto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Calendar getDataPgto() {
        return dataPgto;
    }

    public void setDataPgto(Calendar dataPgto) {
        this.dataPgto = dataPgto;
    }

    public Double getValorPgto() {
        return valorPgto;
    }

    public void setValorPgto(Double valorPgto) {
        this.valorPgto = valorPgto;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

}
