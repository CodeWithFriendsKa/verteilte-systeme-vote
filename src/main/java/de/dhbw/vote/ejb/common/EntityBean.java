/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.vote.ejb.common;

import de.dhbw.vote.jpa.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author codekeks
 * @param <Entity>
 * @param <EntityId>
 */
public abstract class EntityBean<Entity, EntityId> {

    @PersistenceContext
    protected EntityManager em;
    private final Class<Entity> entityClass;

    public EntityBean(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }
    public Entity findById(EntityId id) {
        return em.find(entityClass, id);
    }
    public List<Entity> findAll() {
        String select = "SELECT s FROM $C s".replace("$C", this.entityClass.getName());
        return em.createQuery(select).getResultList();
    }
    public Entity saveNew(Entity entity) {
        em.persist(entity);
        return em.merge(entity);
    }
    public Entity update(Entity entity) {
        return em.merge(entity);
    }
    public void delete(Entity entity) {
        entity = em.merge(entity);
        em.remove(entity);
    }
    public void deleteAll(){
        var users = this.findAll();
        users.forEach(e -> {
        System.out.println("delete user: " + e.toString());
        this.delete(e);
        });
    }
}