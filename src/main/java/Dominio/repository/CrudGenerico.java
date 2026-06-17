package Dominio.repository;

import java.util.List;

public interface CrudGenerico<T,ID> {

    int save(T beans);

    int update(T beans);

    int delete(ID id);

    T findById(ID id);

    List<T> findAll();
}
