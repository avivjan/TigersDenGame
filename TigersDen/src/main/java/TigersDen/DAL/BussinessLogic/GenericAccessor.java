package TigersDen.DAL.BussinessLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import TigersDen.DAL.Contract.*;

public class GenericAccessor<T extends IStorable> implements IGenericAccessor<T> {
    private Map<UUID, T> dataStore;

    public GenericAccessor() {
        dataStore = new HashMap<>();
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(dataStore.values());
    }

    @Override
    public T getById(UUID id) {
        // Assuming UUID is used as the unique identifier
        return dataStore.get(id);
    }

    @Override
    public void add(T item) {
        UUID guid = item.getId();
        dataStore.put(guid, item);
    }

    @Override
    public void update(T item) {
        UUID guid = item.getId();
        if (dataStore.containsKey(guid)) {
            dataStore.put(guid, item);
        }
    }


    @Override
    public void delete(UUID id) {
        dataStore.remove(id);
    }
}