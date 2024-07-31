/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author vanessalagomachado
 */

@Entity
@Table(name = "tb_aluno")
public class Aluno extends Pessoa implements Serializable{
    
    @Column(name = "aluno_data_inicio")
    private Calendar dataInicio;
    
    @Column(name = "aluno_dia_pgto")
    private int dataPgto;

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getDataPgto() {
        return dataPgto;
    }

    public void setDataPgto(int dataPgto) {
        this.dataPgto = dataPgto;
    }

    public Aluno() {
        dataInicio = Calendar.getInstance();
    }
    
    
    
    
}
