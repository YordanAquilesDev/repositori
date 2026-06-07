package Aplicacion.ImplService;

import Dominio.IService.ILogicaParaAnalisisDeNegocio;
import Dominio.Modelo.Animal;
import Dominio.Modelo.Cliente;
import Dominio.Modelo.Pedido;
import Dominio.Modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public class ServiceAnalisisDeNegocioImpl implements ILogicaParaAnalisisDeNegocio {
    Connection conexion;
    public ServiceAnalisisDeNegocioImpl(Connection conexion) {
        this.conexion=conexion;
    }
    @Override
    public Animal animalMasCostoso() {
        try{
            String sql= """
                    SELECT TOP 1
                    a.idAnimal,
                    a.nombreAnimal,
                    a.especie,
                    a.raza
                                    
                    FROM Animal a
                    JOIN LoteAnimal l ON a.id_animal=l.id_animal
                    JOIN ConsumoLote c ON l.idLote= c.idConsumo
                    ORDER BY c.cantidad DESC                             
                    """;

            PreparedStatement prepararParaLaConsulta=conexion.prepareStatement(sql);
            ResultSet resultado=prepararParaLaConsulta.executeQuery();
            return new Animal(
                    resultado.getInt("idAnimal"),
                    resultado.getString("especie"),
                    resultado.getString("raza")

            );


        }catch(Exception e){
            System.out.println("Error al traer el animal mas costoso");
        }
        return null;
    }

    @Override
    public List<Producto> LosProductosMasVendidosPorFecha(Date fecha,Date fecha2) {
        try{
            String sql= """
                    SELECT TOP 5
                    p.idProducto,
                    p.nombreProducto,
                    p.tipoProducto,
                    p.unidadmedida,
                    p.preciounidad,
                    p.stock
                    FROM Producto p
                    JOIN DetalleVenta dv ON p.idProducto=dv.idProducto
                    JOIN Venta v ON dv.idVenta=v.idVenta
                    WHERE v.fecha between ? AND ?                        
            """;
            PreparedStatement prepararParaLaConsulta=conexion.prepareStatement(sql);
            ResultSet resultado=prepararParaLaConsulta.executeQuery();


        }catch(Exception e){

        }


        return List.of();
    }

    @Override
    public double gananciaNetaPorFecha(Date fecha) {
        return 0;
    }

    @Override
    public double gastosNetosPorFecha(Date fecha) {
        return 0;
    }

    @Override
    public List<Pedido> listaDePedidosEntregados() {
        return List.of();
    }

    @Override
    public List<Pedido> listaDePedidosNoEntregados() {
        return List.of();
    }

    @Override
    public Cliente clienteMasConsumidor() {
        return null;
    }
}
