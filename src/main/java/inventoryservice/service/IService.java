package inventoryservice.service;

import java.util.List;

public interface IService <T,ID>{
    T add(T t);
    T update(T t);
    void delete(ID id);
    T get(ID id);
    List<T> getAll();
}