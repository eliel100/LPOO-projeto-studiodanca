/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.dao;

import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model.Modalidade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PersistenciaJPA implements InterfacePersistencia {

    public EntityManager entity;
    private EntityManagerFactory factory;

    public PersistenciaJPA() {
        factory = Persistence.createEntityManagerFactory("pu_studio_danca");
        entity = factory.createEntityManager();
    }

    @Override
    public Boolean conexaoAberta() {
        if (entity == null || !entity.isOpen()) {
            entity = factory.createEntityManager();
        }
        return entity.isOpen();
    }

    @Override
    public void fecharConexao() {
        if (entity != null && entity.isOpen()) {
            entity.close();
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        return entity.find(c, id);
    }

    public List<Modalidade> buscarTodasModalidades() {
        TypedQuery<Modalidade> query = entity.createQuery("SELECT m FROM Modalidade m", Modalidade.class);
        return query.getResultList();
    }

    @Override
    public void persist(Object o) throws Exception {
        try {
            conexaoAberta();
            entity.getTransaction().begin();

            if (o instanceof Modalidade) {
                Modalidade modalidade = (Modalidade) o;
                if (modalidade.getId() != null) {
                    // Se o id não for nulo, verifica se já existe no banco
                    Modalidade existing = entity.find(Modalidade.class, modalidade.getId());
                    if (existing != null) {
                        // Atualiza o existente
                        entity.merge(modalidade);
                    } else {
                        // Caso contrário, persiste uma nova
                        entity.persist(modalidade);
                    }
                } else {
                    // Se não tiver id, persiste como novo
                    entity.persist(modalidade);
                }
            } else {
                entity.persist(o);
            }

            entity.getTransaction().commit();
        } catch (Exception e) {
            if (entity.getTransaction().isActive()) {
                entity.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        try {
            conexaoAberta();
            entity.getTransaction().begin();
            entity.remove(o);
            entity.getTransaction().commit();
        } catch (Exception e) {
            if (entity.getTransaction().isActive()) {
                entity.getTransaction().rollback();
            }
            throw e;
        }
    }
}
