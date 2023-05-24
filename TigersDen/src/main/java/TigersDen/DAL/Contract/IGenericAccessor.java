package TigersDen.DAL.Contract;

interface IGenericAccessor<T extends IStorable> {
    List<T> getAll();
    T getById(int id);
    void add(T item);
    void update(T item);
    void delete(int id);
}