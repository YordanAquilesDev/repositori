package Dominio.Service;
<<<<<<< HEAD
import java.util.List;

public  interface ServiceGenerico<T,ID> {
=======

import java.util.List;
import java.util.Optional;

public interface ServiceGenerico<T,ID> {

>>>>>>> llevarCambio
    int save(T beans);

    int update(T beans);

    int delete(ID id);

<<<<<<< HEAD
    List<T> finAll();

    T finById(ID id);
    

}
=======
    Optional<T> findById(ID id);

    List<T> findAll();

    int saveAndFinId(T beans);
}
>>>>>>> llevarCambio
