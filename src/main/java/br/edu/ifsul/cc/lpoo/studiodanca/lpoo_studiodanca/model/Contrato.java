/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author vanessalagomachado
 */
@Entity
@Table(name = "tb_contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "contrato_id")
    private int id;

    @Column(name = "contrato_data_inicio", nullable = false)
    private Calendar dataInicio;

    @Column(name = "contrato_valor", precision = 2)
    private double valor;

    @Column(name = "contrato_forma_pgto")
    @Enumerated(EnumType.STRING)
    //não incluindo anotação o JPA assume como padrão que será mapeado no BD
    // como inteiro referente ao índice do valor informado no Enum
    // outra opção que podemos usar é o tipo EnumType.STRING
    private FormaPgto forma_pgto;

    @OneToOne
    @JoinColumn(name = "contrato_aluno")
    private Aluno aluno;

@ManyToMany
    @JoinTable(
            name = "tb_itens_contrato",
            joinColumns = @JoinColumn(name = "contrato_id"),
            inverseJoinColumns = @JoinColumn(name = "pacote_id"))
    private List<Pacote> pacotes = new ArrayList<>();


    public Contrato() {
        dataInicio = Calendar.getInstance();
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public FormaPgto getForma_pgto() {
        return forma_pgto;
    }

    public void setForma_pgto(FormaPgto forma_pgto) {
        this.forma_pgto = forma_pgto;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Pacote> getPacotes() {
        return pacotes;
    }

    public void addPacote(Pacote p) {
        pacotes.add(p);
        p.addContrato(this);
    }

}
