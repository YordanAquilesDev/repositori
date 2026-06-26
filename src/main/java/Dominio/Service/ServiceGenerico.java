package Dominio.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceGenerico<T,ID> {

    int save(T beans);

    int update(T beans);

    int delete(ID id);

    Optional<T> findById(ID id);

    List<T> findAll();

    int saveAndFinId(T beans);

}

