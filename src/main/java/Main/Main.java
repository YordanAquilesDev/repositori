/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import Presentacion.CargaDatos;
import Presentacion.HistorialCompras;
import Presentacion.HistorialMovimientos;
import Presentacion.HistorialVentas;
import Presentacion.Home;
import Presentacion.MonitoreoGranja;
import Presentacion.MonitoreoLote;
import Presentacion.MonitoreoPedidos;
import Presentacion.PanelClientes;
import Presentacion.PanelProveedor;
import Presentacion.Stock;

/**
 *
 * @author yordan
 */
public class Main extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());

    private javax.swing.ImageIcon escalarIcono(String ruta, int ancho, int alto) {
        // 1. Cargamos la imagen original grande
        java.net.URL url = getClass().getResource(ruta);
        if (url == null) {
            return null;
        }

        java.awt.Image imgOriginal = new javax.swing.ImageIcon(url).getImage();

        // 2. Redimensionamos la imagen al ancho y alto que le pidamos con calidad SUAVE (SCALE_SMOOTH)
        java.awt.Image imgEscalada = imgOriginal.getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);

        // 3. Devolvemos la imagen ya pequeñita convertida otra vez en Icono
        return new javax.swing.ImageIcon(imgEscalada);
    }

    public Main() {
        initComponents();
        int anchoIcono = 24;
        int altoIcono = 24;
          Home home = new Home();
        cambiarPantalla(home);
        btnMonitPedidos.setIcon(escalarIcono("/recursos/auriculares.png", anchoIcono, altoIcono));
        btnClientes.setIcon(escalarIcono("/recursos/usuarios.png", anchoIcono, altoIcono));
        btnProveedores.setIcon(escalarIcono("/recursos/avatar.png", anchoIcono, altoIcono));
        btnAlmacen.setIcon(escalarIcono("/recursos/capas.png", anchoIcono, altoIcono));
        btnHistCompras.setIcon(escalarIcono("/icon/carro-de-la-compra.png", anchoIcono, altoIcono));
        btnHistMovimientos.setIcon(escalarIcono("/recursos/biblioteca.png", anchoIcono, altoIcono));
        btnHistVentas.setIcon(escalarIcono("/recursos/dinero.png", anchoIcono, altoIcono));
        btnMonitGranja.setIcon(escalarIcono("/recursos/archivo.png", anchoIcono, altoIcono));
        btnMonitLote.setIcon(escalarIcono("/recursos/capas.png", anchoIcono, altoIcono));
        btnSalir.setIcon(escalarIcono("/recursos/cerrar-sesion.png", anchoIcono, altoIcono));
        btnHome.setIcon(escalarIcono("/recursos/hogar.png", anchoIcono, altoIcono));
        btnCargaDatos.setIcon(escalarIcono("/recursos/subir.png", anchoIcono, altoIcono));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelNavegacion = new javax.swing.JPanel();
        btnHistMovimientos = new javax.swing.JButton();
        btnMonitGranja = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnMonitLote = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnHistCompras = new javax.swing.JButton();
        btnHistVentas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnAlmacen = new javax.swing.JButton();
        btnCargaDatos = new javax.swing.JButton();
        btnMonitPedidos = new javax.swing.JButton();
        PanelCabecera = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Contenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelNavegacion.setBackground(new java.awt.Color(204, 204, 255));
        PanelNavegacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHistMovimientos.setBackground(new java.awt.Color(255, 153, 153));
        btnHistMovimientos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHistMovimientos.setText("Movim");
        btnHistMovimientos.setBorderPainted(false);
        btnHistMovimientos.setContentAreaFilled(false);
        btnHistMovimientos.setFocusPainted(false);
        btnHistMovimientos.addActionListener(this::btnHistMovimientosActionPerformed);
        PanelNavegacion.add(btnHistMovimientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 170, 80));

        btnMonitGranja.setBackground(new java.awt.Color(255, 153, 153));
        btnMonitGranja.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMonitGranja.setText("Granj");
        btnMonitGranja.setBorderPainted(false);
        btnMonitGranja.setContentAreaFilled(false);
        btnMonitGranja.setFocusPainted(false);
        btnMonitGranja.addActionListener(this::btnMonitGranjaActionPerformed);
        PanelNavegacion.add(btnMonitGranja, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 170, 70));

        btnSalir.setBackground(new java.awt.Color(255, 153, 153));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setFocusPainted(false);
        btnSalir.addActionListener(this::btnSalirActionPerformed);
        PanelNavegacion.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 880, 170, 50));

        btnMonitLote.setBackground(new java.awt.Color(255, 153, 153));
        btnMonitLote.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMonitLote.setText("Lot");
        btnMonitLote.setBorderPainted(false);
        btnMonitLote.setContentAreaFilled(false);
        btnMonitLote.setFocusPainted(false);
        btnMonitLote.addActionListener(this::btnMonitLoteActionPerformed);
        PanelNavegacion.add(btnMonitLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 170, 70));

        btnHome.setBackground(new java.awt.Color(51, 102, 255));
        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHome.setText("Hom");
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setFocusPainted(false);
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
        });
        btnHome.addActionListener(this::btnHomeActionPerformed);
        PanelNavegacion.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 170, 70));

        btnHistCompras.setBackground(new java.awt.Color(255, 153, 153));
        btnHistCompras.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHistCompras.setText("Comp");
        btnHistCompras.setBorderPainted(false);
        btnHistCompras.setContentAreaFilled(false);
        btnHistCompras.setFocusPainted(false);
        btnHistCompras.addActionListener(this::btnHistComprasActionPerformed);
        PanelNavegacion.add(btnHistCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 170, 70));

        btnHistVentas.setBackground(new java.awt.Color(255, 153, 153));
        btnHistVentas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHistVentas.setText("Vent");
        btnHistVentas.setBorderPainted(false);
        btnHistVentas.setContentAreaFilled(false);
        btnHistVentas.setFocusPainted(false);
        btnHistVentas.addActionListener(this::btnHistVentasActionPerformed);
        PanelNavegacion.add(btnHistVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 170, 70));

        btnProveedores.setBackground(new java.awt.Color(255, 153, 153));
        btnProveedores.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnProveedores.setText("Provee");
        btnProveedores.setBorderPainted(false);
        btnProveedores.setContentAreaFilled(false);
        btnProveedores.setFocusPainted(false);
        btnProveedores.addActionListener(this::btnProveedoresActionPerformed);
        PanelNavegacion.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, 170, 70));

        btnClientes.setBackground(new java.awt.Color(255, 153, 153));
        btnClientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClientes.setText("Client");
        btnClientes.setBorderPainted(false);
        btnClientes.setContentAreaFilled(false);
        btnClientes.setFocusPainted(false);
        btnClientes.addActionListener(this::btnClientesActionPerformed);
        PanelNavegacion.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 170, 70));

        btnAlmacen.setBackground(new java.awt.Color(255, 153, 153));
        btnAlmacen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAlmacen.setText("Stock");
        btnAlmacen.setBorderPainted(false);
        btnAlmacen.setContentAreaFilled(false);
        btnAlmacen.setFocusPainted(false);
        btnAlmacen.addActionListener(this::btnAlmacenActionPerformed);
        PanelNavegacion.add(btnAlmacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 810, 170, 60));

        btnCargaDatos.setBackground(new java.awt.Color(255, 153, 153));
        btnCargaDatos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCargaDatos.setText("Datos");
        btnCargaDatos.setBorderPainted(false);
        btnCargaDatos.setContentAreaFilled(false);
        btnCargaDatos.setFocusPainted(false);
        btnCargaDatos.addActionListener(this::btnCargaDatosActionPerformed);
        PanelNavegacion.add(btnCargaDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 740, 170, 60));

        btnMonitPedidos.setBackground(new java.awt.Color(255, 153, 153));
        btnMonitPedidos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMonitPedidos.setText("Pedi");
        btnMonitPedidos.setBorderPainted(false);
        btnMonitPedidos.setContentAreaFilled(false);
        btnMonitPedidos.setFocusPainted(false);
        btnMonitPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMonitPedidosMouseEntered(evt);
            }
        });
        btnMonitPedidos.addActionListener(this::btnMonitPedidosActionPerformed);
        PanelNavegacion.add(btnMonitPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 170, 70));

        PanelCabecera.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setText("LA GRAN GRANJA");

        javax.swing.GroupLayout PanelCabeceraLayout = new javax.swing.GroupLayout(PanelCabecera);
        PanelCabecera.setLayout(PanelCabeceraLayout);
        PanelCabeceraLayout.setHorizontalGroup(
            PanelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCabeceraLayout.createSequentialGroup()
                .addGap(418, 418, 418)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelCabeceraLayout.setVerticalGroup(
            PanelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCabeceraLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(21, 21, 21))
        );

        Contenedor.setBackground(new java.awt.Color(255, 255, 255));
        Contenedor.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE))
            .addComponent(PanelCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelNavegacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* LISTA DE BOTONES I SUS RESPECTIVOS USOS */
    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        seleccionarBoton(btnHome);
        Home home = new Home();
        cambiarPantalla(home);

    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        // TODO add your handling code here:
        if (!btnClientes.isContentAreaFilled()) { // Si no está seleccionado fijo
            btnClientes.setContentAreaFilled(true);
            btnClientes.setBackground(new java.awt.Color(255, 140, 50)); // Naranja más claro/brillante
        }
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnMonitLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonitLoteActionPerformed
        seleccionarBoton(btnMonitLote);
        MonitoreoLote lotes = new MonitoreoLote();
        cambiarPantalla(lotes);
    }//GEN-LAST:event_btnMonitLoteActionPerformed

    private void btnMonitGranjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonitGranjaActionPerformed
        seleccionarBoton(btnMonitGranja);
        MonitoreoGranja granja = new MonitoreoGranja();
        cambiarPantalla(granja);
    }//GEN-LAST:event_btnMonitGranjaActionPerformed

    private void btnHistMovimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistMovimientosActionPerformed
        seleccionarBoton(btnHistMovimientos);
        HistorialMovimientos movimientos = new HistorialMovimientos();
        cambiarPantalla(movimientos);

    }//GEN-LAST:event_btnHistMovimientosActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        seleccionarBoton(btnClientes);
        PanelClientes vistaClientes = new PanelClientes();
        cambiarPantalla(vistaClientes);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnHistComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistComprasActionPerformed
        seleccionarBoton(btnHistCompras);
        HistorialCompras compras = new HistorialCompras();
        cambiarPantalla(compras);
    }//GEN-LAST:event_btnHistComprasActionPerformed

    private void btnHistVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistVentasActionPerformed
        seleccionarBoton(btnHistVentas);
        HistorialVentas compras = new HistorialVentas();
        cambiarPantalla(compras);
    }//GEN-LAST:event_btnHistVentasActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        seleccionarBoton(btnProveedores);
        PanelProveedor proveedores = new PanelProveedor();
        cambiarPantalla(proveedores);
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlmacenActionPerformed
        seleccionarBoton(btnAlmacen);
        Stock almacen = new Stock();
        cambiarPantalla(almacen);

    }//GEN-LAST:event_btnAlmacenActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCargaDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargaDatosActionPerformed
        seleccionarBoton(btnCargaDatos);
        CargaDatos carga = new CargaDatos();
        cambiarPantalla(carga);
    }//GEN-LAST:event_btnCargaDatosActionPerformed

    private void btnMonitPedidosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMonitPedidosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMonitPedidosMouseEntered

    private void btnMonitPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonitPedidosActionPerformed
        // TODO add your handling code here:
           seleccionarBoton(btnMonitPedidos);
        MonitoreoPedidos carga = new MonitoreoPedidos();
        cambiarPantalla(carga);
        
    }//GEN-LAST:event_btnMonitPedidosActionPerformed

    /**
     *
     * @param args the command line arguments
     *
     */


//MAIN ------> Arranca el Programa

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

    private void seleccionarBoton(javax.swing.JButton botonSeleccionado) {
        javax.swing.JButton[] botones = {
            btnClientes, btnProveedores, btnAlmacen, btnHistCompras,
            btnHistMovimientos, btnHistVentas, btnMonitGranja, btnMonitLote,
            btnSalir, btnHome, btnCargaDatos,btnMonitPedidos
        };

        // Configuramos los márgenes de posición
        // Borde Activo: Empuja el texto 35 píxeles a la derecha (se mueve)
        javax.swing.border.Border bordeActivo = javax.swing.BorderFactory.createEmptyBorder(0, 35, 0, 0);
        // Borde Inactivo: Posición normal a 15 píxeles de la izquierda
        javax.swing.border.Border bordeInactivo = javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 0);

        for (javax.swing.JButton btn : botones) {
            // Aseguramos que todos los botones mantengan el texto alineado a la izquierda por código
            btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

            if (btn == botonSeleccionado) {
                btn.setContentAreaFilled(false); // Mantenlo transparente para que sea elegante
                btn.setForeground(java.awt.Color.BLACK); // Texto resalta en blanco puro
                btn.setFont(btn.getFont().deriveFont(java.awt.Font.BOLD)); // Texto se pone en Negrita
                btn.setBorder(bordeActivo); // ¡El texto se desliza a la derecha!
            } else {
                btn.setContentAreaFilled(false);
                btn.setForeground(new java.awt.Color(230, 230, 230, 180)); // Texto un poco transparente/opaco
                btn.setFont(btn.getFont().deriveFont(java.awt.Font.PLAIN)); // Texto normal
                btn.setBorder(bordeInactivo); // Regresa a su posición original
            }
        }
    }

    private void cambiarPantalla(javax.swing.JPanel nuevaPantalla) {
        // 1. Limpiar el contenido actual del contenedor grande
        Contenedor.removeAll();

        // 2. Insertar la nueva pantalla en el centro del BorderLayout
        Contenedor.add(nuevaPantalla, java.awt.BorderLayout.CENTER);

        // 3. Forzar a Java Swing a que vuelva a dibujar y calcular los componentes
        Contenedor.revalidate();
        Contenedor.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Contenedor;
    private javax.swing.JPanel PanelCabecera;
    private javax.swing.JPanel PanelNavegacion;
    private javax.swing.JButton btnAlmacen;
    private javax.swing.JButton btnCargaDatos;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnHistCompras;
    private javax.swing.JButton btnHistMovimientos;
    private javax.swing.JButton btnHistVentas;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnMonitGranja;
    private javax.swing.JButton btnMonitLote;
    private javax.swing.JButton btnMonitPedidos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}

