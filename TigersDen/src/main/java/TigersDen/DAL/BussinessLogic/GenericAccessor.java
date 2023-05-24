package TigersDen.DAL.BussinessLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import TigersDen.DAL.Contract.*;

public class GenericAccessor<T extends IStorable> implements IGenericAccessor<T> {
    private Map<Integer, T> dataStore;

    public GenericAccessor() {
        dataStore = new HashMap<>();
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(dataStore.values());
    }

    @Override
    public T getById(int id) {
        return dataStore.get(id);
    }

    @Override
    public void add(T item) {
        int id = item.getId();
        dataStore.put(id, item);
    }

    @Override
    public void update(T item) {
        int id = item.getId();
        if (dataStore.containsKey(id)) {
            dataStore.put(id, item);
        }
    }

    @Override
    public void delete(int id) {
        dataStore.remove(id);
    }
}