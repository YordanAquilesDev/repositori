package Dominio.IService;

import Dominio.Modelo.Animal;
import Dominio.Modelo.MovimientoAlmacen;

import java.util.Date;
import java.util.List;

public interface IMovimientos {

    List<MovimientoAlmacen> MovimientoAlmacenEnUnAno(Date fecha);
    List<MovimientoAlmacen> MovimientoAlmacenEnUnMes(Date fecha);
    List<MovimientoAlmacen> MovimientoAlmacenEnPolloConsumoInterno(Animal animal);
    List<MovimientoAlmacen> MovimientoAlmacenEnPolloSalidaAMercado(Animal animal);
    List<MovimientoAlmacen> MovimientoAlmacenEnGallinaConsumoInterno(Animal animal);
    List<MovimientoAlmacen> MovimientoAlmacenEnGallinaSalidaAMercado(Animal animal);
    List<MovimientoAlmacen> MovimientoAlmacenEnCerdoConsumoInterno(Animal animal);
    List<MovimientoAlmacen> MovimientoAlmacenEnCerdoSalidaAMercado(Animal animal);
    List<MovimientoAlmacen> MovimientoAlmacenEnPatoConsumoInterno(Animal animal);
    List<MovimientoAlmacen> MovimientoAlmacenEnPatoSalidaAMercado(Animal animal);




}
