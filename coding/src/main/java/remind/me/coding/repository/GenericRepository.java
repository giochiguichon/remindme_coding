package remind.me.coding.repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericRepository<T> implements IGenericRepository<T>{

    private Class<T> type;

    protected EntityManager entityManager;
    public EntityManager getEntityManager(){
        return entityManager;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager pEntityManager){
        entityManager = pEntityManager;
    }

    public GenericRepository(){
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T save(T entity) {
        entityManager.persist(entity);

        return entity;
    }

    @Override
    public T find(Object key) {
        var entity = entityManager.find(type, key);
        return entity;
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public T update(T entity) {
        entityManager.merge(entity);

        return entity;
    }
}
