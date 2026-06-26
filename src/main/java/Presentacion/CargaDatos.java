/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Presentacion;

import Aplicacion.ServiceImpl.CompraServiceImpl;
import Aplicacion.repositoryimpl.ClienteRepositoryImpl;
import Aplicacion.repositoryimpl.CompraRepositoryImpl;
import Aplicacion.repositoryimpl.ProductoRepositoryImpl;
import Aplicacion.repositoryimpl.ProveedorRepositoryImpl;
import Dominio.Modelo.*;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yordan
 */
public class CargaDatos extends javax.swing.JPanel {
    private final CompraServiceImpl compraService;
  private final CompraRepositoryImpl compraRepository;
  private final ProductoRepositoryImpl productoRepository;
  private final ProveedorRepositoryImpl proveedorRepository;
  private final ClienteRepositoryImpl clienteRepository;
    /**
     * Creates new form CargaDatos
     */
    List<String> nombreCliente= new ArrayList<>();
    List<Integer> idCliente= new ArrayList<>();
    List<String> nombreProveedor= new ArrayList<>();
    List<Integer> idProveedor= new ArrayList<>();
    List<Double> cantidad= new ArrayList<>();
    List<Integer> idProducto= new ArrayList<>();
    List<Producto> productos= new ArrayList<>();
    List<String> nombreProductos= new ArrayList<>();

    public CargaDatos() {
        this.compraService = new CompraServiceImpl();
     this.clienteRepository = new ClienteRepositoryImpl();
     this.productoRepository= new ProductoRepositoryImpl();
     this.compraRepository= new CompraRepositoryImpl();
     this.proveedorRepository= new ProveedorRepositoryImpl();

        initComponents();

        listarProductos().forEach(producto -> {
           nombreProductos.add(producto.getNombre());
           idProducto.add(producto.getIdProducto());
        });
        for(String nombreProducto: nombreProductos){
            cmbProducto.addItem(nombreProducto);

        }
        txtAreaProductos.setText("LISTA DE PRDUSCTOS AGREGADOS ");

        listarProveedores().forEach(proveedor -> {
            nombreProveedor.add(proveedor.getNombre());
            idProveedor.add(proveedor.getIdProveedor());
        });
        for(String nombreProveedor: nombreProveedor){
            cmbProveedor.addItem(nombreProveedor);
        }

    }

    private List<Producto> listarProductos(){
        return productoRepository.findAll();
    }
    private  List<Proveedor> listarProveedores(){
        return proveedorRepository.listarProveedores();
    }
    private List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }
    /**
     * Consfiguracion por defecto de swing
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnProcesarDatos = new javax.swing.JButton();
        cmbCargaDatos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        cmbProveedor = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaProductos = new javax.swing.JTextArea();
        cmbProducto = new javax.swing.JComboBox<>();
        btnAgregarProducto = new javax.swing.JButton();
        txtCantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 0, 0));
        jPanel2.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(153, 255, 153));

        btnProcesarDatos.setText("Procesar");
        btnProcesarDatos.addActionListener(this::btnProcesarDatosActionPerformed);

        cmbCargaDatos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Registrar Compra", "Registrar Venta", "Registrar Consumo Interno" }));

        jLabel1.setText("Proveedor");


        jLabel2.setText("Productos");

        txtAreaProductos.setColumns(20);
        txtAreaProductos.setRows(5);
        jScrollPane2.setViewportView(txtAreaProductos);

        cmbProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAgregarProducto.setText("Agregar");
        btnAgregarProducto.addActionListener(this::btnAgregarProductoActionPerformed);

        jLabel4.setText("Producto");

        jLabel5.setText("cantidad");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmbCargaDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnProcesarDatos)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(31, 31, 31)
                                .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(btnAgregarProducto))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(112, 112, 112)))))
                .addGap(29, 29, 29))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(cmbCargaDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(btnAgregarProducto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                .addComponent(btnProcesarDatos)
                .addGap(98, 98, 98))
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(10, 10, 440, 610);

        jPanel5.setBackground(new java.awt.Color(255, 153, 102));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel5);
        jPanel5.setBounds(500, 10, 500, 610);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {

        if(txtCantidad.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor ingrese un cantidad");
        }

        int indice=-1;
        indice= cmbProducto.getSelectedIndex();
        int idProduct= idProducto.get(indice);
        Producto producto = productoRepository.findById(idProduct).orElse(null);
        if (producto == null) return;
        productos.add(producto);
        String valorString = txtCantidad.getText();
        cantidad.add(Double.parseDouble(valorString));
        txtAreaProductos.append(productos.get(productos.size()-1).getNombre()+"\n");
    }

    private void btnProcesarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarDatosActionPerformed
         int seleccion= cmbCargaDatos.getSelectedIndex();
         int indiceDelComboBox= cmbProveedor.getSelectedIndex();
         int idroveedor= idProveedor.get(indiceDelComboBox);
        Proveedor proveedor= proveedorRepository.buscarPorId(idroveedor);
         int idProveedor;
        switch(seleccion){
            case 0:// registar una Compa
                java.util.Date fecha = new java.util.Date();
                Date fechaSql= new Date(fecha.getTime());
                List<Double> subTotales=new ArrayList<>();

                Compra compra = new Compra(0,fechaSql,proveedor,0);
                List<DetalleCompra> detalles = new ArrayList<>();
                double totalCompra = 0;
                for(int i=0; i<productos.size();i++){
                    double subtotal = productos.get(i).getPrecioUnidad()*cantidad.get(i);
                    subTotales.add(subtotal);
                    totalCompra += subtotal;
                    detalles.add(new DetalleCompra(
                            0,
                            compra,
                            productos.get(i),
                            cantidad.get(i),
                            subTotales.get(i)));
                }
                compra.setTotal(totalCompra);
                compra.setDetalles(detalles);


                int resultadoDelGuardarEnLaDB=compraService.save(compra);
                if(resultadoDelGuardarEnLaDB>0){
                    JOptionPane.showMessageDialog(
                            null, "El compra se ha Reaclizdo con exito");
                }else{
                    JOptionPane.showMessageDialog(
                            null, "Uvo un error  al registrar la compra");
                }
                break;
            case 1:// registrar un venta

                Cliente cliente ;

                break;
            case 2:// registrar un consumo Interno
                break;
        }

    }//GEN-LAST:event_btnProcesarDatosActionPerformed

    private double SumarValores(List<Producto> productos){

        double suma=0;
        for(Producto producto:productos){
            suma+=producto.getPrecioUnidad();
        }
        return suma;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnProcesarDatos;
    private javax.swing.JComboBox<String> cmbCargaDatos;
    private javax.swing.JComboBox<String> cmbProducto;
    private javax.swing.JComboBox<String> cmbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea txtAreaProductos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtProveedor;
    // End of variables declaration//GEN-END:variables
}
