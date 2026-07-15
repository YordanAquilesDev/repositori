package Presentacion.GuiCliente2;

import Dominio.Modelo.Animal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class CarritoPanel extends JPanel {

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Animal", "Cantidad", "Precio Unit.", "Subtotal"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modelo);
    private final List<CarritoItem> items = new ArrayList<>();
    private final JLabel lblTotal = new JLabel("Total: S/ 0.00");

    public CarritoPanel() {
        setLayout(new BorderLayout(0, 12));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(320, 0));
        setBorder(new EmptyBorder(16, 12, 16, 12));

        JLabel titulo = new JLabel("Mi Carrito");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(25, 40, 37));
        add(titulo, BorderLayout.NORTH);

        tabla.setRowHeight(26);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabla.getTableHeader().setBackground(new Color(244, 247, 246));
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(225, 231, 229)));
        add(scroll, BorderLayout.CENTER);

        JPanel panelSur = new JPanel();
        panelSur.setLayout(new BoxLayout(panelSur, BoxLayout.Y_AXIS));
        panelSur.setOpaque(false);

        JPanel panelTotal = new JPanel(new BorderLayout());
        panelTotal.setOpaque(false);
        panelTotal.setBorder(new EmptyBorder(8, 0, 8, 0));
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotal.setForeground(new Color(35, 132, 94));
        panelTotal.add(lblTotal, BorderLayout.WEST);
        panelSur.add(panelTotal);

        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 0, 8));
        panelBotones.setOpaque(false);

        JButton btnComprar = new JButton("Comprar");
        btnComprar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnComprar.setBackground(new Color(35, 132, 94));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFocusPainted(false);
        btnComprar.setBorderPainted(false);
        btnComprar.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        btnComprar.addActionListener(evt -> comprar());

        JButton btnVaciar = new JButton("Vaciar Carrito");
        btnVaciar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnVaciar.setBackground(new Color(220, 53, 69));
        btnVaciar.setForeground(Color.WHITE);
        btnVaciar.setFocusPainted(false);
        btnVaciar.setBorderPainted(false);
        btnVaciar.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        btnVaciar.addActionListener(evt -> limpiar());

        panelBotones.add(btnComprar);
        panelBotones.add(btnVaciar);
        panelSur.add(panelBotones);

        add(panelSur, BorderLayout.SOUTH);
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

    public void quitarSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un item para quitar.");
            return;
        }
        items.remove(fila);
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
        modelo.setRowCount(0);
        for (CarritoItem item : items) {
            modelo.addRow(new Object[]{
                    item.animal.getNombre(),
                    item.cantidad,
                    String.format("S/ %.2f", item.animal.getPrecio()),
                    String.format("S/ %.2f", item.getSubtotal())
            });
        }
        lblTotal.setText(String.format("Total: S/ %.2f", getTotal()));
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
            if (parent instanceof FrmCliente2) {
                ((FrmCliente2) parent).procesarCompra();
            }
        }
    }

    public  static class CarritoItem {

        public final Animal animal;
        public int cantidad;

        CarritoItem(Animal animal, int cantidad) {
            this.animal = animal;
            this.cantidad = cantidad;
        }

        public double getSubtotal() {
            return animal.getPrecio() * cantidad;
        }
    }
}
