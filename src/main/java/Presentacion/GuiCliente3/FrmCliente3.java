/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion.GuiCliente3;

import Aplicacion.Service.ClienteService;
import Aplicacion.Service.VentaService;
import Dominio.Modelo.Cliente;
import Dominio.Modelo.DetalleVenta;
import Dominio.Modelo.Usuario;
import Dominio.Modelo.Venta;
import RunMain.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Optional;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author neker
 */
public class FrmCliente3 extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmCliente3.class.getName());

    private final Usuario usuario;
    private final Cliente cliente;
    private final ClienteService clienteService = new ClienteService();
    private final VentaService ventaService = new VentaService();
    private PanelCatalogo catalogoPanel;
    private PanelCarrito carritoPanel;

    /**
     * Constructor que recibe el usuario logueado.
     */
    public FrmCliente3(Usuario usuario) {
        this.usuario = usuario;
        this.cliente = buscarCliente(usuario.getIdUsuario());

        if (this.cliente == null) {
            JOptionPane.showMessageDialog(null,
                    "No se encontro un perfil de cliente asociado a este usuario.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        initComponents();
        initLogic();
    }

    /**
     * Constructor vacio requerido por NetBeans.
     */
    public FrmCliente3() {
        this.usuario = null;
        this.cliente = null;
        initComponents();
    }

    private Cliente buscarCliente(int idUsuario) {
        Optional<Cliente> opt = clienteService.findByUsuarioId(idUsuario);
        return opt.orElse(null);
    }

    private void initLogic() {
        setTitle("Cliente - Sistema de Granja");
        setMinimumSize(new Dimension(1100, 720));
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        txtBienvenida.setText("Bienvenido, " + usuario.getNombre());

        jButton1.addActionListener(evt -> cerrarSesion());

        jPanelHeader.setBackground(Color.WHITE);
        jPanelHeader.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(225, 231, 229)),
                new EmptyBorder(8, 10, 8, 10)
        ));

        jButton1.setBackground(new Color(220, 53, 69));
        jButton1.setForeground(Color.WHITE);
        jButton1.setFocusPainted(false);
        jButton1.setBorderPainted(false);
        jButton1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        jButton1.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));

        catalogoPanel = new PanelCatalogo();
        carritoPanel = new PanelCarrito();

        getContentPane().remove(jPanelCatalogo);
        getContentPane().remove(jPanelCarrito);

        getContentPane().add(catalogoPanel, BorderLayout.CENTER);
        getContentPane().add(carritoPanel, BorderLayout.EAST);

        revalidate();
        repaint();
    }

    private void cerrarSesion() {
        new Main().setVisible(true);
        dispose();
    }

    public void agregarAlCarrito(Dominio.Modelo.Animal animal) {
        carritoPanel.agregarAnimal(animal);
    }

    public void procesarCompra() {
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "No hay perfil de cliente.");
            return;
        }
        if (carritoPanel.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito esta vacio.");
            return;
        }

        try {
            Venta venta = new Venta();
            venta.setIdCliente(cliente.getIdCliente());

            for (PanelCarrito.CarritoItem item : carritoPanel.getItems()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdAnimal(item.animal.getIdAnimal());
                detalle.setCantidad(item.cantidad);
                detalle.setPrecio(item.animal.getPrecio());
                detalle.setSubtotal(item.getSubtotal());
                venta.addDetalle(detalle);
            }

            int resultado = ventaService.save(venta);

            if (resultado == 1) {
                exportarBoleta();
                carritoPanel.limpiar();
                catalogoPanel.recargar();
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo completar la compra. Intente de nuevo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al procesar la compra: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportarBoleta() {
        if (carritoPanel.getItems().isEmpty()) {
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar Boleta");
        chooser.setSelectedFile(new File("boleta_" + System.currentTimeMillis() + ".txt"));
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de texto", "txt"));

        int resultado = chooser.showSaveDialog(this);
        if (resultado != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = chooser.getSelectedFile();
        if (!archivo.getName().endsWith(".txt")) {
            archivo = new File(archivo.getAbsolutePath() + ".txt");
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            pw.println("========================================");
            pw.println("           GRANJA - BOLETA DE COMPRA");
            pw.println("========================================");
            pw.println();
            pw.println("Cliente: " + usuario.getNombre());
            pw.println("DNI: " + cliente.getDni());
            pw.println("Direccion: " + cliente.getDireccion());
            pw.println("Fecha: " + java.time.LocalDateTime.now().format(
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            pw.println();
            pw.println("----------------------------------------");
            pw.println("  DETALLE DE COMPRA");
            pw.println("----------------------------------------");

            int num = 1;
            for (PanelCarrito.CarritoItem item : carritoPanel.getItems()) {
                pw.printf("%d. %s%n", num++, item.animal.getNombre());
                pw.printf("   Precio: S/ %.2f x %d = S/ %.2f%n",
                        item.animal.getPrecio(), item.cantidad, item.getSubtotal());
            }

            pw.println("----------------------------------------");
            pw.printf("  TOTAL: S/ %.2f%n", carritoPanel.getTotal());
            pw.println("========================================");
            pw.println("       Gracias por su compra!");
            pw.println("========================================");

            JOptionPane.showMessageDialog(this,
                    "Boleta exportada en:\n" + archivo.getAbsolutePath(),
                    "Boleta Exportada", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al exportar boleta: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBienvenida = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanelCarrito = new javax.swing.JPanel();
        jPanelCatalogo = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setText("Catalogo");

        txtBienvenida.setText("Bienvenido, ");

        jButton1.setText("Cerrar cesion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 621, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBienvenida)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelHeader, java.awt.BorderLayout.PAGE_START);

        jPanelCarrito.setPreferredSize(new java.awt.Dimension(320, 700));

        javax.swing.GroupLayout jPanelCarritoLayout = new javax.swing.GroupLayout(jPanelCarrito);
        jPanelCarrito.setLayout(jPanelCarritoLayout);
        jPanelCarritoLayout.setHorizontalGroup(
            jPanelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanelCarritoLayout.setVerticalGroup(
            jPanelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelCarrito, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout jPanelCatalogoLayout = new javax.swing.GroupLayout(jPanelCatalogo);
        jPanelCatalogo.setLayout(jPanelCatalogoLayout);
        jPanelCatalogoLayout.setHorizontalGroup(
            jPanelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 737, Short.MAX_VALUE)
        );
        jPanelCatalogoLayout.setVerticalGroup(
            jPanelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelCatalogo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
        java.awt.EventQueue.invokeLater(() -> new FrmCliente3().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelCarrito;
    private javax.swing.JPanel jPanelCatalogo;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JLabel txtBienvenida;
    // End of variables declaration//GEN-END:variables
}
