package Dominio.Service;

import java.util.List;

public interface ServiceGenerico<T,ID> {

    int save(T beans);

    int update(T beans);

    int delete(ID id);

    T findById(ID id);

    List<T> findAll();
}
