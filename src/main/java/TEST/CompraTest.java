package TEST;

import Aplicacion.repositoryimpl.CompraRepositoryImpl;
import Dominio.Modelo.Compra;
import Dominio.Modelo.DetalleCompra;
import Dominio.repository.CompraRepository;

import java.sql.Date;
import java.util.List;

public class CompraTest {
   static   CompraRepository compraRepository= new CompraRepositoryImpl();

    public static Compra nuevaCompra(DetalleCompra detalleCompra){
       return compraRepository.guardarCompra(detalleCompra);

    }

    public static List<Compra> todasCompras(){
        return compraRepository.listarCompras();
    }

    public static List<Compra> comprasMasAltos(){
        return compraRepository.listarComprasMasAltos();
    }

    public static List<Compra> comprasPorFecha(Date fecha,Date fecha2){
        return compraRepository.listarComprasPorFecha(fecha,fecha2);
    }

}
