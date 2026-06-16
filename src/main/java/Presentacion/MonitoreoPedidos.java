/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Presentacion;

import Aplicacion.repositoryimpl.PedidoRepositoryImpl;
import Dominio.Modelo.Pedido;
import Dominio.repository.PedidoRepository;
import Presentacion.ProcesarAccion.AccionPedidos;

import java.util.List;
import java.util.Objects;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yordan
 */
public class MonitoreoPedidos extends javax.swing.JPanel {

    private final AccionPedidos  accionPedidos = new AccionPedidos();
    /**
     * Creates new form MonitoreoPedidos
     */
    public MonitoreoPedidos() {

        initComponents();
        // Configurar el listener para la JTable tbPedidos
        tbPedidos.getSelectionModel().addListSelectionListener(e -> {
            // Evitamos que se ejecute dos veces (al presionar y soltar)
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = tbPedidos.getSelectedRow();

                // Validamos que haya una fila seleccionada (-1 significa vacío)
                if (filaSeleccionada != -1) {
                    // Convertimos el índice por si la tabla llega a ordenarse
                    int filaModelo = tbPedidos.convertRowIndexToModel(filaSeleccionada);

                    // Llamamos a tu método encapsulado pasando la fila del modelo
                    llenarTxFiel(filaModelo);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ProcesarDatos = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        cmbEstado = new javax.swing.JComboBox<>();
        btnActualizarPedido = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPedidos = new javax.swing.JTable();
        totalPedidos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pedidosEntregados = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pedidosNoEntregados = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pedidosCanselados = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        cmbPedidos = new javax.swing.JComboBox<>();
        btnProcesarPedidos = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        ProcesarDatos.setBackground(new java.awt.Color(153, 153, 153));

        jLabel5.setText("id");

        jLabel6.setText("Cliente");

        jLabel7.setText("Fecha");

        jLabel8.setText("total");

        jLabel9.setText("Estado");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entregado", "Cancelado", " " }));

        btnActualizarPedido.setText("Actualizar");
        btnActualizarPedido.addActionListener(this::btnActualizarPedidoActionPerformed);

        javax.swing.GroupLayout ProcesarDatosLayout = new javax.swing.GroupLayout(ProcesarDatos);
        ProcesarDatos.setLayout(ProcesarDatosLayout);
        ProcesarDatosLayout.setHorizontalGroup(
            ProcesarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProcesarDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProcesarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProcesarDatosLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(56, 56, 56)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ProcesarDatosLayout.createSequentialGroup()
                        .addGroup(ProcesarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGroup(ProcesarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ProcesarDatosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(ProcesarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(ProcesarDatosLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnActualizarPedido))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        ProcesarDatosLayout.setVerticalGroup(
            ProcesarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProcesarDatosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ProcesarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(ProcesarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(ProcesarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(ProcesarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(ProcesarDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addComponent(btnActualizarPedido)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbPedidos);

        totalPedidos.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setText("Total Pedidos Mes");

        javax.swing.GroupLayout totalPedidosLayout = new javax.swing.GroupLayout(totalPedidos);
        totalPedidos.setLayout(totalPedidosLayout);
        totalPedidosLayout.setHorizontalGroup(
            totalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalPedidosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(171, Short.MAX_VALUE))
        );
        totalPedidosLayout.setVerticalGroup(
            totalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalPedidosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pedidosEntregados.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setText("Pedidos Entregados");

        javax.swing.GroupLayout pedidosEntregadosLayout = new javax.swing.GroupLayout(pedidosEntregados);
        pedidosEntregados.setLayout(pedidosEntregadosLayout);
        pedidosEntregadosLayout.setHorizontalGroup(
            pedidosEntregadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pedidosEntregadosLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel2)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        pedidosEntregadosLayout.setVerticalGroup(
            pedidosEntregadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pedidosEntregadosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pedidosNoEntregados.setBackground(new java.awt.Color(204, 255, 255));

        jLabel3.setText("Pedidos Pendientes");

        javax.swing.GroupLayout pedidosNoEntregadosLayout = new javax.swing.GroupLayout(pedidosNoEntregados);
        pedidosNoEntregados.setLayout(pedidosNoEntregadosLayout);
        pedidosNoEntregadosLayout.setHorizontalGroup(
            pedidosNoEntregadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pedidosNoEntregadosLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel3)
                .addContainerGap(137, Short.MAX_VALUE))
        );
        pedidosNoEntregadosLayout.setVerticalGroup(
            pedidosNoEntregadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pedidosNoEntregadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pedidosCanselados.setBackground(new java.awt.Color(204, 255, 255));

        jLabel4.setText("Pedidos Cancelados");

        javax.swing.GroupLayout pedidosCanseladosLayout = new javax.swing.GroupLayout(pedidosCanselados);
        pedidosCanselados.setLayout(pedidosCanseladosLayout);
        pedidosCanseladosLayout.setHorizontalGroup(
            pedidosCanseladosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pedidosCanseladosLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel4)
                .addContainerGap(141, Short.MAX_VALUE))
        );
        pedidosCanseladosLayout.setVerticalGroup(
            pedidosCanseladosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pedidosCanseladosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jTextField1.setText("Mostra Pedidos");
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        cmbPedidos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TotalPedidos", "PedidosEntregados", "PedidosNoEntregados", "PedidosCancelados" }));

        btnProcesarPedidos.setText("Procesar");
        btnProcesarPedidos.addActionListener(this::btnProcesarPedidosActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(btnProcesarPedidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(cmbPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(237, 237, 237))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProcesarPedidos))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ProcesarDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1027, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(totalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pedidosEntregados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(pedidosNoEntregados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pedidosCanselados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pedidosNoEntregados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pedidosEntregados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pedidosCanselados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ProcesarDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnProcesarPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarPedidosActionPerformed
        int indice = cmbPedidos.getSelectedIndex();
        System.out.println("SE PRESIONO EL BOTON PEDIDOS");
        PedidoRepository pedidoRepository = new PedidoRepositoryImpl();
       DefaultTableModel modelo = (DefaultTableModel) tbPedidos.getModel();
        // 1. Definimos el array de columnas con la ortografía correcta
        String[] columnas = {"ID", "Cliente / Razón Social", "Fcha Pedido", "Estado", "Total Comprado"};

        modelo.setDataVector(null, columnas);

        switch (indice) {
            case 0 -> {
                //total pedidos
                if (pedidoRepository.listarPedidos() == null) {
                    System.out.println("LISTA VASIA");
                    break;
                }
                try {
                    System.out.println("LISTANDO TODOS LOS PEDIDOS");
                    List<Pedido> pedidos = pedidoRepository.listarPedidos();
                    System.out.println("TODOS LOS PEDIDOS LISTADOS");

                    //aca esta error
                    for (Pedido p : pedidos) {

                        Object[] fila = new Object[]{
                            p.getIdPedido(),
                            p.getCliente().getNombre(),
                            p.getFecha(),
                            p.getEstado(),
                            p.getTotal()

                        };
                        modelo.addRow(fila);

                    }
                    System.out.println("Todos los pedidos  listos en la tabla");
                } catch (Exception e) {
                    System.out.println("ERROR " + e);
                }
            }
            case 1 -> {
                //pedidos entregados
                if (pedidoRepository.listarPedidosEntregados() == null) {
                    System.out.println("LISTA VASIA");
                    break;
                }
                for (Pedido p : pedidoRepository.listarPedidosEntregados()) {

                    Object[] fila = new Object[]{
                        p.getIdPedido(),
                        p.getCliente().getNombre(),
                        p.getFecha(),
                        p.getEstado(),
                        p.getTotal()

                    };
                    modelo.addRow(fila);

                }
            }
            case 2 -> {
                // pedidos no entregados
                for (Pedido p : pedidoRepository.listarPedidosNoEntregados()) {

                    Object[] fila = new Object[]{
                        p.getIdPedido(),
                        p.getCliente().getNombre(),
                        p.getFecha(),
                        p.getEstado(),
                        p.getTotal()

                    };

                    modelo.addRow(fila);
                }
            }
            case 3 -> {
                //pedidos canselados
                System.out.println("ESTA EN EL 3");
            }
            default ->
                System.out.println("ERROR EN EL CASE");
        }


    }//GEN-LAST:event_btnProcesarPedidosActionPerformed

    private void btnActualizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPedidoActionPerformed
        // TODO add your handling code here:
        int idPedido =Integer.parseInt(txtId.getText());
        String cliente = txtCliente.getText();
        String fecha = txtFecha.getText();
        String estado= Objects.requireNonNull(cmbEstado.getSelectedItem()).toString();



       accionPedidos.modificarPedido(new Pedido());

        
    }//GEN-LAST:event_btnActualizarPedidoActionPerformed

    // Este método recibe la fila mapeada del modelo y distribuye los datos
    private void llenarTxFiel(int fila) {
        try {
            // 1. Extraer los datos de la fila basándonos en el Modelo de tbPedidos
            String id = tbPedidos.getModel().getValueAt(fila, 0).toString();
            String cliente = tbPedidos.getModel().getValueAt(fila, 1).toString();
            String fecha = tbPedidos.getModel().getValueAt(fila, 2).toString();
            String estado = tbPedidos.getModel().getValueAt(fila, 3).toString();
            String total = tbPedidos.getModel().getValueAt(fila, 4).toString();

            // 2. Insertar los strings directamente en tus JTextFields
            txtId.setText(id);
            txtCliente.setText(cliente);
            txtFecha.setText(fecha);
            txtTotal.setText(total);

            // 3. Opcional: Seleccionar automáticamente el estado en el JComboBox
            // Esto buscará "Entregado" o "Cancelado" si coincide con el texto de la tabla
            cmbEstado.setSelectedItem(estado);

        } catch (NullPointerException e) {
            System.out.println("Error: Alguna celda seleccionada contiene un valor nulo. " + e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ProcesarDatos;
    private javax.swing.JButton btnActualizarPedido;
    private javax.swing.JButton btnProcesarPedidos;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbPedidos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel pedidosCanselados;
    private javax.swing.JPanel pedidosEntregados;
    private javax.swing.JPanel pedidosNoEntregados;
    private javax.swing.JTable tbPedidos;
    private javax.swing.JPanel totalPedidos;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
