package Aplicacion.ServiceImpl;

import java.sql.Date;
import java.util.List;

import Aplicacion.repositoryimpl.CompraRepositoryImpl;
import Dominio.Modelo.Compra;
import Dominio.Modelo.DetalleCompra;
import Dominio.Service.ServiceGenerico;
import java.util.Optional;

public class CompraServiceImpl implements ServiceGenerico<Compra,Integer> {
    private final CompraRepositoryImpl compraRepository;

    public CompraServiceImpl() {
        this.compraRepository = new CompraRepositoryImpl();
    }



    public List<Compra> obtenerComprasPorFecha(Date fecha, Date fecha2) {
        return compraRepository.listarComprasPorFecha(fecha, fecha2);
    }

    @Override
    public int save(Compra beans) {
         if (beans== null) {
            return -1;
        }
     return compraRepository.save(beans);
    }

    @Override
    public int update(Compra beans) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Compra> finById(Integer id) {
          return compraRepository.finById(id);
    }

    @Override
    public List<Compra> finAll() {
      return compraRepository.finAll();
    }

    @Override
    public int saveAndFinId(Compra beans) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
