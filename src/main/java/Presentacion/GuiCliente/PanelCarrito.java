/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Presentacion.GuiCliente;

import Dominio.Modelo.Animal;
import java.awt.Color;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author neker
 */
public class PanelCarrito extends javax.swing.JPanel {

    private final List<CarritoItem> items = new ArrayList<>();

    /**
     * Creates new form PanelCarrito
     */
    public PanelCarrito() {
        initComponents();
        initLogic();
    }

    private void initLogic() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"Animal", "Cantidad", "Precio x U", "Subtotal"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblCarrito.setModel(modelo);

        tblCarrito.setRowHeight(26);
        
        //COLORES
        jScrollPane1.setBackground(new Color(200, 200, 200));
        jScrollPane1.getViewport().setBackground(new Color(200, 200, 200));
        tblCarrito.setBackground(new Color(200, 200, 200));
        tblCarrito.getTableHeader().setBackground(new Color(150,150,150));
        tblCarrito.getTableHeader().setForeground(Color.WHITE); 
        
        tblCarrito.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblCarrito.getTableHeader().setBackground(new java.awt.Color(244, 247, 246));
        tblCarrito.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnComprar.addActionListener(evt -> comprar());
        btnVaciarCarrito.addActionListener(evt -> limpiar());
    }

    public void agregarAnimal(Animal animal) {
        for (CarritoItem item : items) {
            if (item.animal.getIdAnimal() == animal.getIdAnimal()) {
                if (item.cantidad < animal.getStock()) {
                    item.cantidad++;
                } else {
                    JOptionPane.showMessageDialog(this,
                            "No hay mas stock disponible para " + animal.getNombre());
                }
                actualizarTabla();
                return;
            }
        }
        if (animal.getStock() <= 0) {
            JOptionPane.showMessageDialog(this, "Este animal no tiene stock disponible.");
            return;
        }
        items.add(new CarritoItem(animal, 1));
        actualizarTabla();
    }

    public void limpiar() {
        items.clear();
        actualizarTabla();
    }

    public List<CarritoItem> getItems() {
        return items;
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(CarritoItem::getSubtotal)
                .sum();
    }

    private void actualizarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tblCarrito.getModel();
        modelo.setRowCount(0);
        for (CarritoItem item : items) {
            modelo.addRow(new Object[]{
                    item.animal.getNombre(),
                    item.cantidad,
                    String.format("S/ %.2f", item.animal.getPrecio()),
                    String.format("S/ %.2f", item.getSubtotal())
            });
        }
        txtTotalCarrito.setText(String.format("Total: S/ %.2f", getTotal()));
    }

    private void comprar() {
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito esta vacio.");
            return;
        }
        int resp = JOptionPane.showConfirmDialog(this,
                "Confirmar compra por S/ " + String.format("%.2f", getTotal()) + "?",
                "Confirmar Compra", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            Component parent = SwingUtilities.getWindowAncestor(this);
            if (parent instanceof FrmCliente) {
                ((FrmCliente) parent).procesarCompra();
            }
        }
    }

    static class CarritoItem implements ArchivoPDF.PDF.CarritoItemData {

        final Animal animal;
        int cantidad;

        CarritoItem(Animal animal, int cantidad) {
            this.animal = animal;
            this.cantidad = cantidad;
        }

        @Override
        public String getNombreAnimal() {
            return animal.getNombre();
        }

        @Override
        public int getCantidad() {
            return cantidad;
        }

        @Override
        public double getPrecioUnitario() {
            return animal.getPrecio();
        }

        @Override
        public double getSubtotal() {
            return animal.getPrecio() * cantidad;
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

        txtTituloCarrito = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtTotalCarrito = new javax.swing.JLabel();
        btnComprar = new javax.swing.JButton();
        btnVaciarCarrito = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCarrito = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        txtTituloCarrito.setBackground(new java.awt.Color(153, 153, 153));
        txtTituloCarrito.setText("     Mi Carrito");
        txtTituloCarrito.setOpaque(true);
        txtTituloCarrito.setPreferredSize(new java.awt.Dimension(53, 30));
        add(txtTituloCarrito, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        txtTotalCarrito.setText("Total: S/ 0.00");

        btnComprar.setBackground(new java.awt.Color(255, 51, 51));
        btnComprar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnComprar.setText("Comprar");
        btnComprar.setOpaque(true);
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        btnVaciarCarrito.setBackground(new java.awt.Color(255, 51, 51));
        btnVaciarCarrito.setText("Vaciar Carrito");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnComprar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalCarrito)
                    .addComponent(btnVaciarCarrito, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtTotalCarrito)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnComprar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVaciarCarrito)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setBackground(new java.awt.Color(153, 153, 153));

        tblCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Animal", "Cantidad", "Precio x U", "Subtotal"
            }
        ));
        jScrollPane1.setViewportView(tblCarrito);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComprarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnVaciarCarrito;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCarrito;
    private javax.swing.JLabel txtTituloCarrito;
    private javax.swing.JLabel txtTotalCarrito;
    // End of variables declaration//GEN-END:variables
}
