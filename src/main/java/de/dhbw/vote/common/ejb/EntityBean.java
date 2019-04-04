package de.dhbw.vote.common.ejb;

import de.dhbw.vote.common.CustomLogger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/***
 * Abstrakte Beanklasse, welche Standartdatenbankzugriffe ermöglicht
 * 
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
public abstract class EntityBean<Entity, EntityId> {
    private final CustomLogger logger;
    @PersistenceContext
    protected EntityManager em;
    private final Class<Entity> entityClass;
    
    /***
     * 
     * @param entityClass 
     */
    public EntityBean(Class<Entity> entityClass) {
        this.entityClass = entityClass;
        this.logger = new CustomLogger(entityClass);
    }
    
    /***
     * 
     * @param id
     * @return 
     */
    public Entity findById(EntityId id) {
        return em.find(entityClass, id);
    }
    
    /***
     * 
     * @return 
     */
    public List<Entity> findAll() {
        String select = "SELECT s FROM $C s".replace("$C", this.entityClass.getName());
        return em.createQuery(select).getResultList();
    }
    
    /***
     * 
     * @param entity
     * @return 
     */
    public Entity saveNew(Entity entity) {
        em.persist(entity);
        return em.merge(entity);
    }
    
    /***
     * 
     * @param entity
     * @return 
     */
    public Entity update(Entity entity) {
        return em.merge(entity);
    }
    
    /***
     * 
     * @param entity 
     */
    public void delete(Entity entity) {
        entity = em.merge(entity);
        em.remove(entity);
    }
    
    /***
     * 
     */
    public void deleteAll(){
        this.findAll().forEach(e -> {
            logger.debug("delete: " + e.toString());
            this.delete((Entity) e);
        });
    }
}