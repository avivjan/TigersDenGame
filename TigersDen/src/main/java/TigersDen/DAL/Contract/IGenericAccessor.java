package TigersDen.DAL.Contract;

import java.util.List;
import java.util.UUID;

public interface IGenericAccessor<T extends IStorable> {
    List<T> getAll();
    T getById(UUID id);
    void add(T item);
    void update(T item);
    void delete(UUID id);
}