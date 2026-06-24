package Dominio.repository;

import java.util.List;
import java.util.Optional;

//              JpaRepository<T,ID>
public interface CrudGenerico<T, ID> {

    int save(T beans);

    int update(T beans);

    int delete(ID id);

    Optional<T> finById(ID id);

    List<T> finAll();

    int saveAndFinId(T beans);
}
