/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model;

import java.io.Serializable;
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
@Table(name="tb_modalidade")
public class Modalidade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "modalidade_id")
    private Integer id;
    
    @Column(nullable = false, length = 155, name = "modalidade_descricao")
    private String descricao;

    //    Validação do Mapeamento Bidirecional: Garantir que o mapeamento @OneToMany em Professor 
//    e @ManyToOne em Modalidade estejam corretamente configurados para refletir o relacionamento bidirecional.
    @ManyToOne
    @JoinColumn(name="modalidade_professor")
    private Professor professor;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
public String toString() {
    return descricao != null ? descricao : "Descrição não disponível";
}

   
    
}
