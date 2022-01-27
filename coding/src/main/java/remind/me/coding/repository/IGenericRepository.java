package remind.me.coding.repository;

public interface IGenericRepository<T> {
    public T save(T entity);
    public T find(Object key);
    public  void delete(T entity);
    public T update(T entity);
}
